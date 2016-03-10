# Learning Android Development
In this repository I store Android applications that were written during my studies from different courses and blog posts.
Such as:
* [Master Android Marshmallow App Development Using Java](http://e.udemymail.com/wf/click?upn=mgiJjRGoxDSTvHaJ6xFuGyROF82LJmQdPYmIgKd6IckeOpfM3iPQebZlnLTYYEdfwR-2BKoGcoDkYB4s6JxpP0MxAGqMQA566Tt-2ByPtLzIEwadnXjLuVmE2wc0DJWh-2BanqZGR6iVO5TEEBcosdBl2SPgYWqHY578Hu3wTT37wOMOU1JO4Kyb-2FL9oPkzwuN6dQiYqJ6gGZOb0a9jr600URzI8ZhN01UABm4ECLiZi-2F5lp-2BM68-2F45z2TKc3i8UKhNX9A_MTmZ5Q-2BZ5cKBEnMkjdV6JAhW-2FFAMs7VV18e8S1SDxKP8m3rEwOjUTb0kIh2HrfqO-2FTOf4ONRV2e6LgwsyihdwtsWTvZlapuLadiqvV9iNd-2FMKnS5PuCcRIuzAVgo2ZXaRRxgiETttIvNEIUIf30zzfGAFeNbPxB0yW1oeq6NBC7136U-2B-2FxZNRFErxl8XFz-2B0CzQO6OfXuktsUpnBiEEkoqWzEtn-2Bkx37tSgWajSd4W-2F2kXrHpvHQpKXA3HJW-2Ftxv8RjGgo1XCKwrDYLk3UiSxIsI3NOmhJzYEBqhZJ1-2FEdhxzuPF0xu69FLDXUYhvCuTLEc4wyZoN7BDKlgX9-2Fsd5w-3D-3D)
course on [Udemy.com](https://www.udemy.com/courses/)
* Hands on Android tutorials on [Raywenderlich.com](https://www.raywenderlich.com/category/android)

and so on.

Here the list of applications:
* Hello World
* Button Counter
* Calculator
* Top 25 Free Apps
* The YouTube Player
* Flickr Browser

### [Hello World](../master/HelloWorld)
This's just _Hello World_ app.

<img src="https://github.com/vanyaland/LearningAndroidDevelopment/blob/master/Screenshots/HelloWorld/hello-world-main-activity.png" width="240" height="400">

### [Button Counter](../master/ButtonClicker)
This app shows the number of clicks on the "Press Me" button.

<img src="https://github.com/vanyaland/LearningAndroidDevelopment/blob/master/Screenshots/ButtonCounter/button-counter-main-activity.png" width="240" height="400">

### [Calculator](../master/Calculator)
Perform mathematical operations on two numbers and displays the result of calculation.

<img src="https://github.com/vanyaland/LearningAndroidDevelopment/blob/master/Screenshots/Calculator/calculator-main-activity.png" width="240" height="400">

### [Top 25 Free Apps](../master/Top25FreeApps)
This app uses [Apple RSS Feed](http://www.apple.com/rss/) to retrive the content about the **_Top 25 Free Apps_**. Returned data is in JSON format.
Also you can parse the other RSS feeds by passing other feed link to this place in the code in **MainActivity.java**.
```
DownloadData downloadData = new DownloadData();
downloadData.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=25/xml");
```

<img src="https://github.com/vanyaland/LearningAndroidDevelopment/blob/master/Screenshots/Top25FreeApps/free-apps-main-activity.png" width="240" height="400">

### [The YouTube Player](../master/YouTubePlayer)
This app uses [YouTube API](https://developers.google.com/youtube/) to watch YouTube content. If you want run this app, then you probably need real physical device, because on AVD doesn't have YouTube installed and other necessary services.

YouTube _video_ and _playlist_ ids are **hardcoded** in the code, if you want to watch other content, then replace this ids with your own.


<img src="https://github.com/vanyaland/LearningAndroidDevelopment/blob/master/Screenshots/YouTubePlayer/youtube-player-main-activity.png" width="240" height="400">

### [Flickr Browser](../master/FlickrBrowser)
Search for photos using [Flickr API](https://www.flickr.com/services/developer) in it's public photos feed.
You can search for multiple tags and take a look on photos in detail activity.
Also using a powerful image downloading and caching third party library [Picasso](http://square.github.io/picasso/).

<img src="https://github.com/vanyaland/LearningAndroidDevelopment/blob/master/Screenshots/FlickrBrowser/flickr-browser-main-activity.png" alt="Main Activity" width="240" height="400">
<img src="https://github.com/vanyaland/LearningAndroidDevelopment/blob/master/Screenshots/FlickrBrowser/flickr-browser-photo-detail-activity.png" alt="Photo Detail Activity" width="240" height="400">
