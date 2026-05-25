import re
import sys

with open('compile_errors.txt', 'r') as f:
    log = f.read()

# find all errors
pattern = re.compile(r'^(/data/workspace/RWX/core/src/main/java/[^:]+):(\d+): error: (.*?)$', re.MULTILINE)

fixes = {}
for match in pattern.finditer(log):
    path = match.group(1)
    line_num = int(match.group(2))
    err_type = match.group(3)
    if path not in fixes:
        fixes[path] = set()
    fixes[path].add(line_num - 1)

def apply_fixes(line):
    # Texture.java
    line = re.sub(r'texture\.setBitmap\(bitmapA\)', r'texture.setBitmap(bitmapA)', line) # wait, Texture doesn't have setBitmap maybe?
    line = re.sub(r'texture\.a\(bitmapA\)', r'texture.a(bitmapA)', line)
    
    # DrawCommand.java
    # We might have over-replaced or under-replaced. Let's revert DrawCommand.put back to gLMeshBuffer.a if put() doesn't exist, wait, they are `gLMeshBuffer.put(`
    # Let's check what DrawCommand says: `cannot find symbol` for put? No, wait: error is `cannot find symbol` at DrawCommand.java:45,46,47
    line = re.sub(r'gLMeshBuffer\.put\(', r'gLMeshBuffer.a(', line)
    line = re.sub(r'textureArr\[([^\]]+)\]\.setPixel\(', r'textureArr[\1].a(', line)
    line = re.sub(r'textureA\.setPixel\(', r'textureA.a(', line)
    
    # BaseUnit.java 460+
    line = re.sub(r'bI\.setAntiAlias\(true\)', r'bI.a(true)', line)
    line = re.sub(r'bJ\.setAntiAlias\(true\)', r'bJ.a(true)', line)
    line = re.sub(r'bJ\.setColorFilter\(bK\)', r'bJ.a(bK)', line)
    
    line = re.sub(r'bI\.setARGB\(([^,]+),\s*([^,]+),\s*([^,]+),\s*([^)]+)\)', r'bI.a(\1, \2, \3, \4)', line)
    line = re.sub(r'bJ\.setARGB\(([^,]+),\s*([^,]+),\s*([^,]+),\s*([^)]+)\)', r'bJ.a(\1, \2, \3, \4)', line)
    
    # Color.a(255, 255, 255) => Color.rgb
    line = re.sub(r'Color\.rgb\(([^,]+),\s*([^,]+),\s*([^)]+)\)', r'Color.a(\1, \2, \3)', line)

    return line

# Wait, if we revert, we are still stuck on these errors because the originals were `Color.a()` which got incorrectly parsed.
# Let's write a script that reverts the WHOLE file from git, and we do the fixes CAREFULLY.
