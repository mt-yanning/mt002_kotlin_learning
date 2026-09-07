# Kotlin Programming Guide

A guide to programming in Kotlin, used in Weeks 1-5 of COMP2850.

Files used for the classroom programming tasks mentioned in this guide are
in the [kotlin-work][work] repository.

Solutions to these programming tasks are in [kotlin-solutions][sol].

## Development

This guide is written using [Jupyter Book][jb] and [MyST][my].

To develop material for it:

1. Fork this repository.
2. Clone the fork to your PC.
3. Install the [uv Python package manager][uv], if you don't already have it.
4. Use the command `uv run juypter book` followed by
   - `build` to build the site, under the `_build` directory
   - `start` to run the preview server locally, rebuilding on changes
   - `clean` to remove all generated files

When you attempt to build or serve the site for the first time, uv will
create a virtual environment and install the dependencies into it.

After committing and pushing any changes, you can issue a pull request
using the GitHub UI.

### Just Command Runner

If you have the [just command runner][jst] installed, you can use these
recipes:

| Recipe     | Abbrev | Purpose                                |
|------------|--------|----------------------------------------|
| `build`    | `b`    | Build the site                         |
| `serve`    | `s`    | Run the preview server locally         |
| `clean`    | `c`    | Remove generated site files            |
| `pristine` | `p`    | Remove generated files & site template |

For example, to run the preview server, do

    just s

## Publishing

The site is published from the `main` branch via GitHub Actions.

The publish action will be triggered if you merge a PR into `main` using the
GitHub UI, or push new commits from a clone of the parent repository.


[work]: https://github.com/COMP2850/kotlin-work/
[sol]: https://github.com/COMP2850/kotlin-solutions/
[jb]: https://jupyterbook.org/stable
[my]: https://mystmd.org/guide
[uv]: https://docs.astral.sh/uv/
[jst]: https://just.systems/man/en/
