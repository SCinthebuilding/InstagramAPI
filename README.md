
![Logo](https://storage.googleapis.com/pr-newsroom-wp/1/2018/11/Spotify_Logo_CMYK_Green.png)


# Spotify API

A Java wrapper for Spotify's Web API.
## Installation

This library is available on [Maven Central](https://search.maven.org/artifact/de.sonallux.spotify/spotify-web-api-java)
 and requires at least Java 11.

 ### With Maven
 Latest official release:
 ```xml
<dependency>
    <groupId>se.michaelthelin.spotify</groupId>
    <artifactId>spotify-web-api-java</artifactId>
    <version>7.1.0</version>
</dependency>
```
    
### Authorization
Please see Spotify's Authorization Guide[ Spotify's Authorization Guide](https://developer.spotify.com/dashboard/) too!
For authorization requests the API object requires at least to have [your application](https://developer.spotify.com/dashboard/)'s client ID and client secret set as its properties.Those properties will then be automatically used by functions that depend on them.
```java
SpotifyApi spotifyApi = new SpotifyApi.Builder()
  .setClientId("<your_client_id>")
  .setClientSecret("<your_client_secret>")
  .build();
```


## Tasks
This project is to find the 10 most streamed songs by artist in spotify.  
You can run the code and type any artist's name in spotify you want to know.
We need to print instruction on console.  
Prepare Presentation about actual project.  
Try next level projects  
+ Medium : show list of songs, when user picks album
+ Hard :  Mix in songs from similar artist
## Examples

* Artists
    * Get an artist
    * Get an artist's albums
    * Get an artist's top tracks

* Albums
    * Get an album's tracks

## License

java-spotify-wrapper is licensed under the MIT license, more about that can be found [here](https://opensource.org/licenses/MIT).

