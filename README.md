# RegLicenseGiver
<p align="center">
  <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/License_plate_in_Russia_2.svg/1280px-License_plate_in_Russia_2.svg.png" alt="Preview"><br/>
</p>

<b>RegLicenseGiver</b> is an ultimate tool for generating vehicle registration plate text. This tool provides REST service that gives all the required info for new plate in plain text.

----

## Table of Contents

  * [New to RegLicenseGiver?](#new-to-rlg)
  * [Building](#building)

## New to RLG?

RegLicenseGiver provides REST service on the localhost, using port 8080.

### How to use it

Examples of working `GET` requests:

> `GET http://localhost:8080/number/random`\
> `GET http://localhost:8080/number/next`\
> `GET http://localhost:8080/number/prev`

Examples of working debug `GET` requests:

> `GET http://localhost:8080/number/debug/silent/next`\
> `GET http://localhost:8080/number/debug/savecurrent`\
> `GET http://localhost:8080/number/debug/presence`\
> `GET http://localhost:8080/number/debug/set/chars/end`

## Building

You need to get the source code. Using git, [clone this repository](https://help.github.com/articles/cloning-a-repository/).

If you're using [GitHub Desktop](https://desktop.github.com), you need to perform additional steps. After cloning the RegLicenseGiver repository, go to the `Repository` menu and select `Open in Command Prompt`. If you don't have Git (not GitHub Desktop) installed, you can follow the prompts to to install Git for your command line. Once Git is installed, use this same process to get to the command prompt. Then, run the above commands.

### On Windows

On Windows, you need to install [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/) or an Ultimate version of IntelliJ IDEA.

## Code of Conduct

> By participating in the RegLicenseGiver community, all members will adhere to maintaining decorum with respect to all humans, in and out of the community. Members will not engage in discussion that inappropriately disparages or marginalizes any group of people or any individual. Members will not attempt to further or advance an agenda to the point of being overbearing or close minded (such as through spreading FUD). Members will not abuse services provided to them and will follow the guidance of community leaders on a situational basis about what abuse consists of. Members will adhere to international law. If members notice a violation of this code of conduct, they will not engage but will instead contact the leader of this project.

> Do not attempt to circumvent or bypass the code of conduct by using clever logic or reasoning (e.g., insulting other people, because they weren't directly mentioned here).

## Contributors

Thanks goes to these wonderful people:

<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><a href="https://github.com/vxxxxxsxxxxxxxx"><img src="https://avatars.githubusercontent.com/u/67050812?v=4" width="100px;" alt=""/><br /><sub><b>Creator</b></sub></a><b/></td>
  </tr>
</table>
<!-- markdownlint-enable -->
<!-- prettier-ignore-end -->
