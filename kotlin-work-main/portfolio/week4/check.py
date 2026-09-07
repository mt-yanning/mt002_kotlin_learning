# Tests to check output from 'To Do' list demo program
# DO NOT ALTER ANYTHING IN THIS FILE!

import sys
import subprocess
import unittest

OS_INFO = """
This script cannot be used on Microsoft Windows systems!

Please run it in a Linux or macOS environment.
"""

COMMAND = [
    "kotlin",
    "--log-level=off",
    "run",
    "-m",
    "to-do-list",
    "to-do-list/data/to-do.csv",
    "2026-10-16",
]

EXPECTED_OUTPUT = b"""Full list:
2026-10-16: NotStarted: Go shopping for food
2026-10-16: Done: Fix bike puncture
2026-10-17: Done: Buy birthday card for Mum
2026-10-19: InProgress: COMP2850 portfolio task
2026-10-21: NotStarted: Book train ticket

In Progress items:
2026-10-19: InProgress: COMP2850 portfolio task

Items due on 2026-10-16:
2026-10-16: NotStarted: Go shopping for food
2026-10-16: Done: Fix bike puncture
"""


def check_for_windows():
    if sys.platform == "win32":
        sys.exit(OS_INFO)


def run(command):
    return subprocess.run(command, capture_output=True, check=False)


class ToDoListTest(unittest.TestCase):
    def test_output(self):
        result = run(COMMAND)
        self.assertEqual(result.stdout, EXPECTED_OUTPUT)
        self.assertEqual(result.returncode, 0)


if __name__ == "__main__":
    check_for_windows()
    unittest.main()
