InstagramPostViewAndroid
===============

 [ ![Download](https://api.bintray.com/packages/pratheepchowdhary/maven/InstagramPostViewAndroid/images/download.svg) ](https://bintray.com/pratheepchowdhary/maven/InstagramPostViewAndroid/_latestVersion)
 [![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://bintray.com/pratheepchowdhary/maven/InstagramPostViewAndroid/_latestVersion)

[InstagramPostViewAndroid](https://www.androidhunt.in)  is an android libary to displays Instragram Embed Post Content To Your Android Appliaction Essaily With less Stuff

<div align="center">
        <img width="40%" src="https://raw.githubusercontent.com/pratheepchowdhary/InstagramPostViewAndroid/master/screenshot/Screenshot_in.androidhunt.instasample.png" alt="While Post Loading" title="While Post Loading"</img>
        <img height="0" width="8px">
        <img width="40%" src="https://raw.githubusercontent.com/pratheepchowdhary/InstagramPostViewAndroid/blob/master/screenshot/Screenshotin.androidhunt.instasample.png" alt="After Post Loading" title="After Post Loading"></img>
</div>



Usage
-----

**1.** Add the following to your **build.gradle**.
```groovy
dependencies {
  implementation 'in.androidhunt.instapost:InstagramPostViewAndroid:1.0.3'
}
```
**2.** In my **layout** xml
```xml
    <in.androidhunt.instapost.InstaPostView
        android:layout_width="match_parent"
        android:id="@+id/insta_post_view"
        android:layout_height="wrap_content"/>
```

**3.** Now in java code we set the content now
```java
        InstaPostView instaPostView = (InstaPostView)findViewById(R.id.insta_post_view);
        String instragmPostId="Bm8kaROlodJ";
        //setting the post id the view
        instaPostView.setPostId(instragmPostId);
        
        // or If Want Show Instragram Post From Html Content by call your self Instagram api
        // https://api.instagram.com/oembed/?url=http://instagr.am/p/Bm8kaROlodJ 
        //set html post content parsed from above instagram api
        instaPostView.setPostContent("Here The Html Post Content");
        
        
        
```

Licences
--------
    Copyright 2019 Pratheep Chowdhary.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
