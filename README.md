# VIE 

A tool designed to generate code from templates both local and global.

# Basics 

Templates are stored in a hierarchy broken up into three levels. A typical path to a template looks like  
`<TLC>storehouse-name/storeroom-name/template-name` where TLC is the _start_ character.

All of names in the path are case insensitive. 

Below we describe the full and unambiguous way to specify the paths to a template. In is much easier in practice. 
So after you read the section on the different types of storehouses go down to the practical usage area and 
will discover the simple way this can be used.   


## Global Storehouses

Global store houses are store houses that are available to anyone who uses the machine. 
The global store house locations vary with the OS they are typically stored in a folder with the name 
`.recreate/storehouses/`

There is one global storehouse it is populated upon start up initialization that is called the master global store house and
it is stored on the file system with the name master somthing like: `.vie/storehouses/master`

Paths to the global storehouses start with the `/` character. i.e. `/storehouse-name/storeroom-name/template-name`

To get to the global master store house we can use the name `master` or just the `/` character again.
i.e. `/master/storeroom-name/template-name` or `//storeroom-name/template-name`

Throughout the rest of the document we will use `//` to reference the master global storehouse 

## User Storehouses

User store houses are store houses that are available and managed by the specific user.
The user store house locations is typically stored in a user folder named `~/username/.vie/storehouses`

A specific user storehouse can be designated as the master user store house.

Paths to the user storehouses start with the `>` character. i.e. `>storehouse-name/storeroom-name/template-name`

To get to the master user store house we can use the `>` character again.
i.e. `>>storeroom-name/template-name`

Throughout the rest of the document we will use `>>` to reference the master user storehouse

## Local Storehouses 

Local store houses are store houses anywhere in the file system but in order to use them user must register the 
local store house with **_Vie_**.

Paths to the local storehouses start with the `.` character. i.e. `.storehouse-name/storeroom-name/template-name`

`.storeroom-name/template-name`

## In Practice

In practice using this is much easier than describing it. You simply specify the name of the template on
the command line and **_Vie_** will look for the match at the nearest level.

For example to use the _msc_ template you simply specify the name on the command line 
and **_Vie_** will look for the nearest version of that template. 

### Where it looks 

If the user tells **_Vie_** to create code using just a template name _msc_ i.e. `vie gen -t msc ..additional params..`

1. It starts by looking for any templates named _msc_ in store houses at the same level as the current working 
directory (**cwd**).
   1. If there is a local store house immediately below the **cwd** and it has a single template named _msc_ , use that template
      1.If there are multiple local store houses immediately below the **cwd** with a template named _msc_
       1. Look for templates named _msc_ in all local, user and global store houses
       2. Print out possible matches
       3. Exit
   2. If there is a local store house immediately below the folders above the **cwd** and it has a single template named _msc_ , use that template
      1.If there are multiple local store houses immediately below the folders above the **cwd** with a template named _msc_
       1. Look for templates named _msc_ in all local, user and global store houses
       2. Print out possible matches
       3. Exit
   3. If there is a single user store house with a template named _msc_, use that template
      1. If there are multiple user store houses with a template named _msc_
         1. Look for templates named _msc_ in all local, user and global store houses
         2. Print out possible matches
         3. Exit
   4. If there is a single global store house with a template named _msc_, use that template
      1. If there are multiple global store houses with a template named _msc_
      2. Look for templates named _msc_ in all local, user and global store houses
         1. Print out possible matches
         2. Exit

## Getting Started 




# Tech Stack

## Micronaut 3.2.7 Documentation

- [User Guide](https://docs.micronaut.io/3.4.1/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.4.1/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.4.1/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

## Feature lombok documentation

- [Micronaut Project Lombok documentation](https://docs.micronaut.io/latest/guide/index.html#lombok)

- [https://projectlombok.org/features/all](https://projectlombok.org/features/all)

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

## Feature mockito documentation

- [https://site.mockito.org](https://site.mockito.org)

