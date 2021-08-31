# Progression

Progression is a serverside mod that makes it simple to stop players from entering the Nether or End Dimensions. This mod is intended to be used when starting up a new server and wanting to stop players from immediately accessing the Nether or killing the Ender Dragon. Alternatively, you can also just disable the accessing the Nether and End by normal means. You can still access them if there are other methods you have set up, but Nether Portals and End Portals will no longer work.

It is not recommended to re-disable any of these dimensions, as players may end up being stuck in the Nether or End Dimensions with no way to escape other than dying.

## Installation and Config

Simply place the jar into your server's mod folder and start up your server. Edit the config to enable or disable the nether. You do not need to run a command or restart the server for changes to take effect, just edit the values and save the file.

Config:

```json5
{
  DisableEndDimension: true, // true to disable, false to enable it
  DisableNetherDimension: true, // true to disable, false to
  DisplayDisableMessages: true, // whether or not to show the disabled dimension messages
  EndDisabledMessage: '-[ The End Dimension is currently disabled by your Server Admin ]-',
  NetherDisabledMessage: '-[ The Nether Dimension is currently disabled by your Server Admin ]-',
  IsDefaultSettings: false, // change this to true to reset to default settings on next reload
}
```
