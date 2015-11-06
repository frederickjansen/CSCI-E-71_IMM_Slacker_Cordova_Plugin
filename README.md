# Slack Services plugin for Apache Cordova
Final project for the group **International Mix Mob**

##Product Vision
Create Cordova plugins for iOS and Android to enable Slack services within development of any hybrid mobile application. The plugin includes:
* Authorization service
* Identity Information
* Selecting Channels
* Posting Messages
* Presence Awareness
* Advance Content sharing via Slash Commands
* An example Cordova application to demostrate the plugin's features.


## Team members
### Scrum Team
* Cornell Wright, _iOS Developer_
* Alpana Barua, _Scrum Master & part-time iOS Developer_
* Jeffry Pincus, _Android Developer_
* Frederick Jansen, _Android & Hybrid Developer_
* Manoj Shenoy, _iOS Developer_
* Shameek Nath, _Android Developer_
* Justin Sanford, _Android & Hybrid Developer_
* Evan Borysko, _Product Owner & part-time iOS Developer_

### Product owner
Evan Borysko

### Scrum Master
Alpana Barua

## Definition of Done
* Builds without errors.
* All public methods are documented.
* All classes are documented.
* Build and deployment steps are documented.
* Code is committed to Github.
* Code is reviewed by at least one other team member (pair/mobs need no other review) before committed to master.
* All existing unit tests pass, and new ones are written for new code.
* Additional unit tests code reviewed by 2 other team members (pair/mobs need one other review).
* Code coverage is > 85%.
* Code targets 4 platforms: iOS, Android, JS API and reference application.
* Ticket must be closed after a story is finished.
* Time is logged in Jira for all closed tickets.
* All commits must reference a ticket number.
* The product owner signs off on a closed ticket.
* Product has matched or exceeded the expectation of stake holders after product review.

## Stakeholders
* **Mobile App Developer** - needs a small lightweight plugin that allows easy integration into their app to authenticate in Slack, must have high levels of code readability and transparency and the documentation needs to be clear and logical.
* **Mobile Hybrid Web Developer**- needs a plugin that is platform agnostic, authentication should be fast and reliable with clear error descriptions.
* **Product Owner** - needs something that is fast to implement, needs to have few impediments to getting a product to market and enhances the productivity of the developer team.

## Resources
* [JIRA - Backlog and Sprint Boards](https://harvard-coursework.atlassian.net/secure/RapidBoard.jspa?rapidView=1&projectKey=SKCP&view=planning.nodetail&selectedIssue=SKCP-14&epics=visible)
* [Pointing Poker](https://www.pointingpoker.com/64137)

## Git workflow
The idea is to follow a story branch pattern very similar to the one outlined [here](http://reinh.com/blog/2009/03/02/a-git-workflow-for-agile-teams.html), with the main difference being that no interactive rebase is required to squash commits. The steps are thus as follows:

Pull in latest content when on master.
```
git pull origin master
```
Create and checkout story branch. Use the ticket number as the start of the branch name
```
git checkout -b 80-git-workflow
```
Rebase from master often to keep up to date with changes. Fix any merge conflicts as they come along.
```
git fetch origin master
git rebase origin/master
```
When all work is done on this story, rebase with master once more. Then finally, merge the branch with master.
```
git checkout master
git merge 80-git-workflow
```
When followed correctly, no merge conflicts should ever appear on the master branch.

## Preparing Development Environment
The sources of this brief instruction list are the Cordova [Android Platform Guide](https://cordova.apache.org/docs/en/5.1.1/guide/platforms/android/index.html).
1. Install [Node.js](https://nodejs.org/).  As of 11/4/2015, use [v0.12.0](https://nodejs.org/download/release/v0.12.0/).  [Node Version Manager](https://github.com/creationix/nvm) is a great tool to manage multiple versions on one machine.
2. Install [Java Development Kit](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
3. Install [Android Studio](http://developer.android.com/sdk/installing/index.html?pkg=studio).
4. Open the Android SDK Manager (type `android` on console) and install:
   * Android 5.1.1 (API 22)/SDK platform
   * Tools/Android SDK Build-tools version 19.1.0 or higher
   * Tools/ARM System Image OR Tools/Intel x86 Atom System Image
   * Extras/Android Support Repository
   * (optional) Extras/Intel x86 Emulator Accelerator (if you installed Atom System Image)
5. Install Cordova:
   * `npm install -g cordova`
6. Create a new project:
   * `cordova create hello com.example.hello HelloWorld`
   * `cd hello`
   * `cordova platform add android`
   * `cordova build`

# Tools for development using XCode

1. Mac with OS X 10.10.5 installed
2. Install [Node.js 0.12]https://nodejs.org/download/release/v0.12.0/)
2. Install Xcode 7.1 from Mac App Store
3. Install [Git](http://sourceforge.net/projects/git-osx-installer/files/git-2.6.2-intel-universal-mavericks.dmg/download?use_mirror=autoselect)
4. Install Cordova using the below command
		* $ sudo npm install -g cordova
5. Create a new project:
   * `cordova create harvardcscie71 com.example.hello HelloWorld`
   * `cd harvardcscie71`
   * `cordova platform add ios`
   * `cordova build`
Optional - Install Github desktop from https://desktop.github.com/
Optional - If you have multiple versions of node.js and need to switch between them using homebrew versions
Install HomeBrew  using below command in terminal
   * ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
   * brew tap homebrew/versions


