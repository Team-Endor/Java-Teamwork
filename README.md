# Team Endor  

Team Endor - Java game for the Java Basics course at SoftUni.

## The Meteor
Fall to Rise.

#### The Game:

You fall from the Sky, you have to make it to the ground

## Project Desription
## Classes

#### Display
-- Display extends Canvas

-- Uses JFrame and Canvas

-- Adjust settings

#### Game
-- Game implements Runnable

-- The engine of the game

-- init() - initialises objects used in the game: Display, InputHandler, Assets, Player, Enemy

-- tick() - updates information about the objects

-- render() - visualizes objects in the game 

-- run() - sets the FPS; keeps the game running :)

-- start() - creates a new thread

-- stop() - stops the thread by joining it to the main thread

#### InputHandler
-- InputHandler implements KeyListener

-- sets keys used in the game

#### Launcher
-- Launches the game

#### Player
-- creates a new playable object

-- defines Position(x,y), health, velocity, moving directions, Player Image, Bounding box

-- intersects() - checks for collision with objects

-- tick() - updates information about the Player

-- render() - visualizes the Player in the game 

#### Assets
-- makes loading of images faster

-- define different player's/ entitie's images here

-- crop images

#### ImageLoader
-- buffers images

#### SpriteSheet
-- gets sub image of sprite sheet

#### State => Game State => State Manager
-- TODO - not implemented yet
-- gets the different states of the game: Menu, Game, End

#### Resources
-- Library of images / texts
