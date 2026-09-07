# Doing The Work

## Pacing

You should aim to have read this guide and completed its tasks by the
end of Week 5.

We recommend that you pace yourself as shown in the table below. This will
ensure that you have covered the material needed for each portfolio assignment.
Feel free to move through the material quicker than this if you like.

| Week | Sections                                                      |
|------|---------------------------------------------------------------|
|   1  | First Steps, Types & Variables, Intro to I/O                  |
|   2  | Flow Control, Functions, Unit Testing 1, Collection Types     |
|   3  | Lambda Expressions, Error Handling, Null Safety, OO Modelling |
|   4  | Creating & Using Classes, Unit Testing 2, Organizing Classes  |
|   5  | Inheritance & Polymorphism, Abstract Classes, Interfaces      |

## Using GitHub

**As you work through the tasks, remember to commit and push to GitHub!**

You should commit your work regularly, using informative commit
messages&mdash;e.g., after completing each task.

You don't need to push after every commit, but you should definitely push
at the end of a work session. This will ensure you have backups of everything
on GitHub.

You should also get into the habit of _pulling_ from GitHub at the start of
every work session. If you routinely pull at the start and push at the end,
you will be able to synchronize your work across multiple clones of the
repository. This will allow you to easily switch from working on a SoCS Linux
machine to working on your own PC, and vice versa.

## Running Scripts

In most of these tasks, you will run a script to compile and execute your
Kotlin code. The instructions given in this guide assume the use of Linux or
macOS, and on those platforms the script name needs to be prefixed with
`./`.

If you are using Windows, scripts need to be run differently. If your command
prompt is provided by `cmd.exe`, you should omit the leading `./` from the
command. If you are using Windows Powershell then you will need to use
`.\` as a prefix instead.

:::{warning} Script Permissions
If you see a 'permission denied' error when attempting to run a script on
a Linux or macOS system, you can enable execute permission using the `chmod`
command.

For example, in the case of the `kotlin` script, you would enter

    chmod u+x kotlin
:::
