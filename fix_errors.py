import re
import sys

def process_compile_errors():
    with open('/root/.gemini/tmp/rwx/tool-outputs/session-718d15a9-b31f-42d5-8672-0118f818376c/run_shell_command_proxy-ba98f4664ad3fad8.txt', 'r') as f:
        log = f.read()

    errors = {}
    for line in log.split('\n'):
        if line.startswith('/data/workspace/RWX/core/src/main/java/'):
            parts = line.split(':')
            if len(parts) >= 4 and 'error' in parts[3]:
                path = parts[0] + ':' + parts[1]
                line_num = int(parts[2])
                err_msg = ':'.join(parts[3:]).strip()
                if path not in errors:
                    errors[path] = []
                errors[path].append({'line': line_num, 'msg': err_msg})
                
    return errors

print(process_compile_errors())
