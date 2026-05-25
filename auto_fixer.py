import re
import os

with open('compile_errors.txt', 'r') as f:
    log = f.read()

errors = {}
# Regex to match the java compiler error format
# e.g. /path/to/file.java:123: error: cannot find symbol
pattern = re.compile(r'^(/data/workspace/RWX/core/src/main/java/[^:]+):(\d+): error: (.+?)$', re.MULTILINE)

lines = log.split('\n')
for i, line in enumerate(lines):
    match = pattern.match(line)
    if match:
        path = match.group(1)
        line_num = int(match.group(2))
        err_type = match.group(3)
        
        # Read the next few lines for context
        code_line = lines[i+1] if i+1 < len(lines) else ""
        pointer_line = lines[i+2] if i+2 < len(lines) else ""
        
        if path not in errors:
            errors[path] = []
            
        errors[path].append({
            'line': line_num,
            'type': err_type,
            'code': code_line,
            'pointer': pointer_line,
        })

print(f"Found errors in {len(errors)} files.")

# Now let's try to fix them!
files_changed = 0
for path, file_errors in errors.items():
    with open(path, 'r') as f:
        file_lines = f.readlines()
        
    changed = False
    for err in file_errors:
        line_idx = err['line'] - 1
        if line_idx >= len(file_lines):
            continue
            
        orig_line = file_lines[line_idx]
        new_line = orig_line
        
        code = err['code']
        # If it's "cannot find symbol"
        if 'cannot find symbol' in err['type']:
            if 'Color.a(' in code and 'method a(int,int,int,int)' in "\n".join(lines): 
                # wait, the symol details are in lines[i+3]
                pass
                
    # Better approach: We can just apply some safe regexes on the specific lines that threw an error!
    # Because if a line threw an error, we know it needs fixing.
    
