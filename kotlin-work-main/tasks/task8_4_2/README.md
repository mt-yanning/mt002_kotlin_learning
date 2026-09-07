# Task 8.4.2

A demonstration of the performance benefits of sequences.

`Benchmark.kt` contains code to operate on a text file in two different ways,
with and without using sequences.

The name of the file should be supplied on the command line. We have provided
a large file (Leo Tolstoy's *War And Peace*) for testing purposes. This is
over 3 MB in size and has over 66,000 lines of text.

## Linux & macOS

Run the application with

    ./run war-and-peace.txt

(This executes the Kotlin Toolchain's `run` task with logging turned off.)

You will see timings for an operation performed on this text file, with and
without the use of sequences.

## Windows

If `cmd.exe` is your shell, run the application with

    run war-and-peace.txt

In Windows PowerShell, do

    .\run.bat war-and-peace.txt
