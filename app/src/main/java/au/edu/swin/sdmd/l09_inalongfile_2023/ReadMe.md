L09InAFile with concurrency
============================

Extends `L08InAFile` project with a long running data-management task, which is executed in a thread using Coroutine.

Class `LongTaskFragment` has 3 versions:
1. `LongTaskFragment`: failed to update button from the long-running task
2. `LongTaskFragmentFix1`: solution 1, which involves a minimum code change
3. `LongTaskFragmentFix2`: solution 2, which uses ViewModel, LiveData and Observer

To switch between the above versions, update function `SectionsPagerAdapter.getItem()` 
(section number `3`) with the right class.