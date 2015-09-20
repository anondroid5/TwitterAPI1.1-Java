# TwitterAPI1.1
[!Language](http://img.shields.io/badge/language-java-orange.svg?style=flat)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
[!Library](https://img.shields.io/badge/library-twitter4j-blue.svg?style=flat)](http://twitter4j.org/ja/index.html)
[!License](http://img.shields.io/badge/license-apache2.0-lightgrey.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

# Requirements
* Java >= 1.8
* Tomcat >= 7.0
* maven2 (compile)

http://mvnrepository.com/

# Details

### demo
Investigation.java - 研究で使用したプログラム(Public Streamからハッシュタグを指定してデータ取得)

UserFollowers.java - 特定ユーザのフォロワーを調査する

UserInfo.java - 特定ユーザの情報を調査する

### twitterrest
Followersids.java - 特定ユーザのフォロワー一覧を得る

GeoSearch.java - 緯度、経度を指定して区間内のtweetを取得

GetHomeTimeline.java - ホームタイムラインの取得

SearchTweet.java - 検索ワードを指定してtweetを取得

Timeline.java - 特定ユーザのホームタイムラインを取得

Tweet.java - ホームタイムラインに投稿

UserSearch.java - 特定ユーザのtweet検索

### public_streaming
GeoStream.java - PublicStreamの緯度、経度によるフィルター

HashtagStream.java - PublicStreamのハッシュタグによるフィルター

KeywordStream.java - PublicStreamのキーワードによるフィルター

OAuthStream.java - OAuthを行ったユーザのPublicStream

PublicStream.java - PublicStream

SampleStream.java - PublicStream上のユーザ情報の表示

UserIDStream.java - 特定ユーザIDで対象者のPublicStreamを取得

### site_streaming
SiteStream.java - SiteStream

### user_streaming
UserStream.java - UserStream






# Author
Fumihiko Akagi

# Copyright
    Copyright (C) 2015 Fumihiko Akagi
    Copyright (C) 2007 Yusuke Yamamoto
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
