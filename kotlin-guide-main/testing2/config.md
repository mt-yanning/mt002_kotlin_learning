# Kotest Configuration

We have already discussed how to configures aspects of Kotest's behaviour
via system properties set in a `kotest.properties` file.

An alternative approach is to create a project configuration object:

```{code} kotlin
:filename: ProjectConfig.kt
package io.kotest.provided

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.IsolationMode

object ProjectConfig : AbstractProjectConfig() {
    override val globalAssertSoftly = true
    override val isolationMode = IsolationMode.InstancePerRoot
}
```

The example above softens assertions across the whole project and ensures
that all test classes use 'instance per root' as the isolation mode.

Kotest allows project configuration to be put in class definition or an
object definition. We use `object` here because this makes more sense. The
configuration is a singleton: there can be only ever be one instance of
it in any project.

:::{note}
This approach is more object-oriented and potentially more flexible than
setting system properties, but be aware that Kotest has some very particular
rules about where the `ProjectConfig` object is located in the project. See
the Kotest documentation on [project-level configuration][cfg] for further
details.
:::


[cfg]: https://kotest.io/docs/framework/project-config.html
