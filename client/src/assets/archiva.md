# Apache Archiva Private Repo

## Installing Archiva (Docker)
* The installation has been done in .87 server using the following compose file:

``` yaml
version: '3.4'
services:
  archiva:
    image: xetusoss/archiva:latest
    volumes:
      - type: volume
        source: archiva-data-vol
        target: /archiva-data
    environment:
      SMTP_HOST: your-smtp-server
    ports:
      - 4326:8080

volumes:
  archiva-data-vol:
```

```bash
Run this command to start the archiva
$ docker-compose up -d
```



## Uploading Rudisha to Archiva
<!-- ![Maven Setting](https://raw.githubusercontent.com/mutisyap/io/main/assets/archiva.png = 100x20) -->
<img src="https://raw.githubusercontent.com/mutisyap/io/main/assets/archiva.png" alt="drawing" width="500"/>

Remember to select the second option in part 1

## Using Guest _Without Auth_
After uploading the artifact, proceed to the repository using the artifact: E.g Notification and add the following in pom.xml

``` xml
<repositories>
    <repository>
        <id>archiva</id>
        <url>http://[IP]:4326/repository/snapshots/</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
```

The snapshots can be replaced with internal depending in the repository id used in the upload section.

## Next steps
Try building notification service now.

## What next?
* Restricting repository access to authenticated users only.
* Automated artifact upload