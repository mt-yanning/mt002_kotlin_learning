# Setting Up

Work for this module can be done on SoCS Linux machines, on your own computer,
or in both of those environments, as you see fit.

:::{danger} Important
**We do not recommend, and cannot support, use of GitHub Codespaces
in COMP2850.**

Codespaces are limited environments that are not well-suited to a lot of the
work you will be doing in this module. We _strongly_ recommend that you spend
some time setting up the considerably more powerful environments provided by
our Linux machines and your own PC.
:::

## Using Kotlin

We will be using the [**Kotlin Toolchain**][ktc] for many of the tasks in
this guide.

**This is already installed on SoCS lab machines.** To make it accessible,
enter `module add kotlin` in a terminal window before attempting any tasks
in that terminal window.

We recommend that you also install the toolchain on your own PC. See the
[installation instructions][inst] for details of how to do this.

The section [First Steps With Kotlin][next] will introduce you to the
toolchain and show you some of the ways in which you can use it.

## Editing Source Code

You can use any editing environment you like to work on the tasks in this
guide.

[Microsoft VS Code][vsc] and the [IntelliJ IDEA][ij] development environment
are sensible choices that offer some advantages over other tools.

## Git & GitHub

**All of your work in COMP2850 is done in Git repositories, hosted in GitHub.**

For this first part of the module, we have provided a repository for 
classroom tasks and portfolio work. **Your first step should be forking this
repository.**

After this, you should clone your fork to your SoCS filestore _and_ to your
own PC. You should also make sure that your Git identity is configured
properly. Guidance on doing all of this is provided below.

### Installing Git

:::{note}
This is NOT necessary on SoCS machines. Git is already available there.
:::

On your own PC, check if you have Git installed and set up correctly by
entering

    git

at a command line prompt. If you get a 'command not found' error then you'll
probably need to install it yourself:

- If you are running Linux, you can do this using your distribution's package
  manager.

- If you have a Mac, you can install Git as part of the [Xcode command line
  tools][clt], or via the [Homebrew][brew] package manager.

- If you are running Windows, then you should install the [Git for
  Windows][gfw] distribution. (Note that we recommend the use of Linux, Mac
  or [Windows Subsystem for Linux][wsl] rather than using Windows natively,
  although the latter is certainly possible.)

### Setting up an SSH Key

You may have done this last year for COMP1850&mdash;in which case you can
skip this step and go straight to [Cloning][clon] below.

In a terminal window[^win], create an SSH key pair with a command like this:

    ssh-keygen -t ed25519 -C "USERNAME@leeds.ac.uk"

Be sure to substitute your University of Leeds username for `USERNAME` here!

:::{danger} Important
When prompted, press {kbd}`Enter` to accept the default file location.

Also, make sure that you provide a secure passphrase. Note that you will
not see the passphrase characters echoed in the terminal!
:::

Start the SSH agent if necessary:

    eval "$(ssh-agent -s)"

Then add your SSH key pair to it:

    ssh-add ~/.ssh/id_ed25519

Display the public key in the terminal, with

    cat ~/.ssh/id_ed25519.pub

Copy *everything* that was displayed by this command to the clipboard.

:::{danger} Important
**Don't forget the `.pub` here!**

You want to transfer the *public* key to GitHub; the private key should
remain in your filestore and be kept secret.
:::

In your browser, go to github.com and login if necessary, then click on
your profile icon at the top-right of the page and choose *Settings* &gt;
*SSH and GPG Keys*. Then click on the green *New SSH key* button.

Paste the contents of the clipboard into the space provided. Give the key a
recognizable title that identifies the environment you are using (e.g.,
"My Laptop" or "School of Computer Science"), then click *Save*.

See the GitHub guides to [connecting using SSH keys][conn] and
[troubleshooting common problems][prob] if you need more help with this.

### Cloning The Repository

In your browser, go to github.com. Login if necessary and find the home
page for the forked repository.

Click on the green *Code* button, select the 'Local' tab and choose SSH as
the Clone option. Copy the repository URL to the clipboard.

In a terminal window on a SoCS Linux PC or your own PC, navigate to where
you want to clone the repository. Type `git clone`, then paste in the
repository URL that you copied in the previous step. End the command with
the name you want to use for the directory that contains the repository, then
press {kbd}`Enter`

### Configuring Your Identity

In a terminal window, navigate to the cloned repository and enter commands
like these:

    git config user.name "MY NAME"
    git config user.email USERNAME@leeds.ac.uk

Replace `MY NAME` with your real name, and `USERNAME` with your University
username.

**Make sure this has been done in all clones of the forked repository.**

Setting your identity correctly will become particularly important in
Semester 2, when you work in teams on your projects. We will be looking at
the contributions made by individual team members to the project, and
identities help us to assess this.

:::{note}
These settings configure your identity locally, within the cloned
repositories only.

If you want to make this configuration global, across all uses of Git on
a particular machine, include the option `--global`, like so:

```
git config --global user.name "MY NAME"
git config --global user.email USERNAME@leeds.ac.uk
```
:::

[^win]: If you are using Windows natively, this terminal should be the 'Git
Bash' terminal provided by the Git for Windows distribution.

[clt]: https://mac.install.guide/commandlinetools/
[brew]: https://brew.sh/
[gfw]: https://git-scm.com/downloads/win
[wsl]: https://learn.microsoft.com/en-gb/windows/wsl/
[clon]: required.md#cloning-the-repository
[conn]: https://docs.github.com/authentication/connecting-to-github-with-ssh
[prob]: https://docs.github.com/authentication/troubleshooting-ssh
[ktc]: https://kotlin-toolchain.org/
[inst]: https://kotlin-toolchain.org/latest/cli/#installation
[next]: ../first/hello.md
[vsc]: vscode.md
[ij]: intellij.md
