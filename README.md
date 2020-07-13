g# Movies

Just a test-application

### Paging
It was my first experience with paging and I didn't find enough suitable information about this topic. But I tried to do it on my own. I really want to say that it works, but... not really as I wanted.
How should it work(my thoughts)? The app makes a request every time when user sees ~3/4 of the all amount of the items and then adds a response data to the recycler. For the first 3-5 requests it really works, but "Waiting for a blocking GC Alloc" and then GC kills app because of using a big amount of memory. I think the reason for this is that the app gets and store lots of high-resolution pictures (maybe even too much). What I think about it:
- I can make pictures a bit worse, but it's temporary and on 10's request everything will be the same
- I can delete photos, when they are unnecessary(but now I should make requests for every photo two or even more times)
- Cache them as it's realized in Glide, Picasso
- __Also I really want to hear your opinion on this kind of problem and how it should be fixed__
