# dev-setup

## evrythng-tools

Toolchain for evrythngers.

### Setup

- Clone the repo.
- Maven install evrythng-tools.
- Create an alias to run it.
```
vi ~/.profile
---
alias et='java -jar ~/Documents/workspaces/evrythng-idea-java/dev-setup/evrythng-tools/target/evrythng-tools-1.0-SNAPSHOT-jar-with-dependencies.jar'
```
Now you can open a new shell and type 'et'.

### Development Guidelines

- Every command must support a help subcommand.
- There is no user guide other than the software itself.
