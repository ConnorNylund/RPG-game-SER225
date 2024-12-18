---
layout: default
title: Player Class Overview
nav_order: 1
parent: Player
grand_parent: Game Code Details
permalink: /GameCodeDetails/Player/PlayerClassOverview
---

## Table of contents
{: .no_toc .text-delta }

1. TOC
{:toc}

---

# Player Class Overview

## Player Resources Setup

The `Player` class has several instance variables that need to be setup upon creation.

`Player` subclasses should set the `walkSpeed` value as desired, which is the speed at which the player character moves at when walking around the map.

The `Player` has several other important variables it uses to keep track of its current state:
- **playerState** -- based on the `PlayerState` enum in the `Level` package, a `Player` can be in a certain state which affects its game logic; currently, the supported states are `STANDING`, `WALKING`, and `INTERACTING`
- **facingDirection** -- which direction the player is facing; can be either `LEFT` or `RIGHT`

## Player Class Methods

The player's `update` cycle handles a ton of different cases based on the current `PlayerState`, 
and the `Player` class has split up most of that state logic into separate methods. 
For example, standing logic is in a separate method to walking logic, etc.
The `handlePlayerState` method determines which game logic method should be called based on the `playerState` instance variable:

```java
protected void handlePlayerState() {
    switch (playerState) {
        case STANDING:
            playerStanding();
            break;
        case WALKING:
            playerWalking();
            break;
        case INTERACTING:
            playerInteracting();
            break;
    }
}
```

## Player Subclasses

Players to be used in game are defined by subclassing the `Player` class. 
These are found in the `Player` package.
The cat player is defined by the `Cat` class. 

The subclass doesn't have to do much, as the `Player` class contains all the standard player functionality. 
If a change is going to be specific to one player, then it can be added to a subclass.
Any changes that need to affect all players should be done in the  base `Player` class.

The `Cat` subclass sets movement related attributes to desired values.
Additionally, it defines the player's animations.
The `Player` class expects certain animations to be included in a player subclass by name, all of which are included in the `Cat` subclass,
although this can be easily changed and additional animations can be added freely. 
Currently, the `Player` class expects the following animations. 

1. `STAND_RIGHT` and `STAND_LEFT` -- player standing still
1. `WALK_RIGHT` and `WALK_LEFT` -- player walking

Looking at the `Cat` class's constructor is a good reference point for making an additional player.

```java
public Cat(float x, float y) {
    super(new SpriteSheet(ImageLoader.load("Resources/Cat.png"), 24, 24), x, y, "STAND_RIGHT");
    walkSpeed = 2.3f;
}
```

The image file spritesheet for the cat player is `Cat.png`.

## Player Moving

When the player moves through the level, you may notice that most of the time the player is not actually moving around screen, and instead stays in the middle of the screen while the map's camera moves to show more of the map. 
The only time the player actually moves is when the map's camera reaches the end of the map and has no more map to show. 
You can see this behavior in the below gif. 
Notice how the player is kept in the middle of the screen a majority of the time while the camera continually shows different parts of the map.

![game-screen-1.gif](../../../assets/images/playing-level.gif)

While the player is in charge of the overall movement of the game, the map class's `adjustMovementX` and `adjustMovementY` methods are called each frame to adjust the player's position and the camera's position as needed. 
Usually, what happens is the player will move forward while at the center of the screen, and then the adjust methods will move the player back to the center of the screen and move the camera instead to show more of the map. 
This gives the appearance that the map is "scrolling". 
And yes, nearly all games do this: 99% of the time your player character is not actually moving; instead the map's camera is.

## Player Update Cycle

Each `update` cycle, the `Player` class will carry out the below steps:
1. Handle player state (more details on player state [here](./player-states.md))
1. Move player by the amount it should be moved by based on the results of the handle player state step
1. Handle player animation updates and see if a switch is needed
1. Call super class's update cycle in order to apply animation logic (`super.update();`)