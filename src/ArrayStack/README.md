### Array Stack

The code to decrease the length of the array is similar to the  code that increases it. I've included a minimum size on the
array: it only shrinks if it has more than ten elements. This is because, if the is very small, it will fill or empty with just a few operations, so we'll end up shrinking and growing it very often.

If we decrease by 50% when the the array drops to 50% full 50% full, then the resulting array will be 100% full. This means that, if the next operation is a push(), we will have to immediately increase the size of the array again. But, now, the array
is only 50% full so, if the next operation is a pop(), we'll
shrink it again, and so on. This can lead to a lot of wasteful copying.

By decreasing only when the array is 25% full, the new array is
itself only half-full so it will take many more pushes or pops
before the size needs to change again. Similarly, when the array
is expanded, it is half-full, which is a long way from the point
where it shrinks (25% full) or grows again (100% full).

This is a good general principle if you're designing a structure that grows when it's full and shrinks when it's becoming empty: ensure that growing and shrinking both put the structure in a state where it won't need to grow or shrink again for a while.
