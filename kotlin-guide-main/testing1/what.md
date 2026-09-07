# What Are Unit Tests?

**Unit tests** are the tests that a programmer writes for the piece of code
that they are currently developing. Unit tests therefore test **one small
part of the system, in isolation from other parts**.

Testing can never prove that software has been implemented correctly[^dijk],
but if you've written a comprehensive suite of unit tests that all pass,
you can be much more confident that what you have written meets the
requirements. **Code without unit tests is essentially untrustworthy**, and
many programmers would regard such code as unfinished.

Note that unit testing is not the only form of testing needed in software
projects. Depending on the nature of a project, we may also need:
**integration testing**, where we test whether components of the system
work properly in combination with each other; **acceptance testing**, where
we verify with users that the functionality they require has been delivered;
**stress testing**, where we determine whether the system copes adequately
under heavy load; and **penetration testing**, where security specialists
probe a system to identify its vulnerabilities.

Conceptually, a large number of small, fast unit tests form the base of a
'test pyramid' (@fig-test-pyramid). Above this, we have a smaller number of
integration tests, which are larger in size, run more slowly, and are run less
frequently. At the top of the pyramid are the full end-to-end tests. These
are the largest and slowest of all. There will be far fewer of these than
there are unit tests in a typical system, and they will be run much less
frequently.

```{figure} test-pyramid.svg
:label: fig-test-pyramid
:enumerator: 6.1
:alt: An inverted pyramid with a base representing unit tests and an apex representing end-to-end tests
Relationship between test size/complexity & number of tests
```


[^dijk]: In his 1972 Turing Award Lecture, renowned computer scientist
[Edsger Dijkstra][ed] said "Program testing can be a very effective way to
show the presence of bugs, but it is hopelessly inadequate for showing
their absence."

[ed]: https://en.wikipedia.org/wiki/Edsger_W._Dijkstra
