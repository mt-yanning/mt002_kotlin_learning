# Introduction to I/O

In this section we start by considering how a Kotlin program can receive
[input via the command line][cmd], as part of the command that invoked the
program.

After that, we focus on basic console I/O operations&mdash;i.e., those that
involve [reading from standard input][cin] or [writing to standard output][cout],
once the program is aleady running.

We conclude with a brief look at some of the ways in which [I/O operations
involving files][file] can be carried out in Kotlin.

After completing this section, you will understand how to handle command line
arguments, how to interact with the console, how to format data for console
output, and how to perform simple read/write operations on files.

:::{note}
We consider only very simple approaches to file I/O in this section.
We will defer consideration of better and more efficient approaches until
[later][iter].
:::


[cmd]: cmdline.md
[cin]: input.md
[cout]: output.md
[file]: files.md
[iter]: ../flow/file-iter.md
