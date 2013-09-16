Arthur bloomfilter
===========
这是使用Java实现的布隆过滤器。
根据data_set的大小自动计算错误率最低时需要的bit数（注：使用hash函数为8个）。
可以用来做String的查重，在有一定错误率的情况下，能够较大的减少存储空间。
具体解释请查看我的博客文章：arthur503.github.io/blog 

参考资料：
吴军：数学之美
July：海量数据处理博客
