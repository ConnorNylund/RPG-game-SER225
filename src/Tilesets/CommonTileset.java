package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;

// This class represents a "common" tileset of standard tiles defined in the CommonTileset.png file
public class CommonTileset extends Tileset {

    public CommonTileset() {
        super(ImageLoader.load("CommonTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // grass
        Frame grassFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassTile = new MapTileBuilder(grassFrame);

        mapTiles.add(grassTile);

        // sign
        Frame signFrame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .withBounds(1, 2, 14, 14)
                .build();

        MapTileBuilder signTile = new MapTileBuilder(signFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(signTile);

        // sand
        Frame sandFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder sandTile = new MapTileBuilder(sandFrame);

        mapTiles.add(sandTile);

        // rock
        Frame rockFrame = new FrameBuilder(getSubImage(3, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder rockTile = new MapTileBuilder(rockFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(rockTile);

        // tree trunk with full hole
        Frame treeTrunkWithFullHoleFrame = new FrameBuilder(getSubImage(2, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeTrunkWithFullHoleTile = new MapTileBuilder(grassFrame)
                .withTopLayer(treeTrunkWithFullHoleFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(treeTrunkWithFullHoleTile);

        // left end branch
        Frame leftEndBranchFrame = new FrameBuilder(getSubImage(2, 4))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder leftEndBranchTile = new MapTileBuilder(grassFrame)
                .withTopLayer(leftEndBranchFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(leftEndBranchTile);

        // right end branch
        Frame rightEndBranchFrame = new FrameBuilder(getSubImage(2, 4))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder rightEndBranchTile = new MapTileBuilder(grassFrame)
                .withTopLayer(rightEndBranchFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(rightEndBranchTile);
        
        // tree trunk
        Frame treeTrunkFrame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeTrunkTile = new MapTileBuilder(grassFrame)
                .withTopLayer(treeTrunkFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(treeTrunkTile);

        // tree top leaves
        Frame treeTopLeavesFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeTopLeavesTile = new MapTileBuilder(grassFrame)
                .withTopLayer(treeTopLeavesFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(treeTopLeavesTile);
        
        // yellow flower
        Frame[] yellowFlowerFrames = new Frame[] {
                new FrameBuilder(getSubImage(1, 2), 65)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(1, 3), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 2), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 4), 65)
                        .withScale(tileScale)
                        .build()
        };

        MapTileBuilder yellowFlowerTile = new MapTileBuilder(yellowFlowerFrames);

        mapTiles.add(yellowFlowerTile);
        // purple flower
        Frame[] purpleFlowerFrames = new Frame[] {
                new FrameBuilder(getSubImage(0, 2), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(0, 3), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(0, 2), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(0, 4), 65)
                        .withScale(tileScale)
                        .build()
        };

        MapTileBuilder purpleFlowerTile = new MapTileBuilder(purpleFlowerFrames);

        mapTiles.add(purpleFlowerTile);

        // middle branch
        Frame middleBranchFrame = new FrameBuilder(getSubImage(2, 3))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder middleBranchTile = new MapTileBuilder(grassFrame)
                .withTopLayer(middleBranchFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(middleBranchTile);

        // tree trunk bottom
        Frame treeTrunkBottomFrame = new FrameBuilder(getSubImage(2, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeTrunkBottomTile = new MapTileBuilder(treeTrunkBottomFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(treeTrunkBottomTile);

        // mushrooms
        Frame mushroomFrame = new FrameBuilder(getSubImage(2, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder mushroomTile = new MapTileBuilder(mushroomFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(mushroomTile);


        // grey rock
        Frame greyRockFrame = new FrameBuilder(getSubImage(3, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder greyRockTile = new MapTileBuilder(greyRockFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(greyRockTile);

        // bush
        Frame bushFrame = new FrameBuilder(getSubImage(3, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder bushTile = new MapTileBuilder(bushFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(bushTile);

        // house body
        Frame houseBodyFrame = new FrameBuilder(getSubImage(3, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder houseBodyTile = new MapTileBuilder(houseBodyFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(houseBodyTile);

        // house roof body
        Frame houseRoofBodyFrame = new FrameBuilder(getSubImage(4, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder houseRoofBodyTile = new MapTileBuilder(grassFrame)
                .withTopLayer(houseRoofBodyFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(houseRoofBodyTile);

        // left house roof
        Frame leftHouseRoofFrame = new FrameBuilder(getSubImage(4, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder leftHouseRoofTile = new MapTileBuilder(grassFrame)
                .withTopLayer(leftHouseRoofFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(leftHouseRoofTile);

        // right house roof
        Frame rightHouseRoofFrame = new FrameBuilder(getSubImage(4, 1))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder rightHouseRoofTile = new MapTileBuilder(grassFrame)
                .withTopLayer(rightHouseRoofFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(rightHouseRoofTile);

        // left window
        Frame leftWindowFrame = new FrameBuilder(getSubImage(4, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder leftWindowTile = new MapTileBuilder(leftWindowFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(leftWindowTile);

        // right window
        Frame rightWindowFrame = new FrameBuilder(getSubImage(4, 2))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder rightWindowTile = new MapTileBuilder(rightWindowFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(rightWindowTile);

        // door
        Frame doorFrame = new FrameBuilder(getSubImage(4, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder doorTile = new MapTileBuilder(doorFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(doorTile);

        // top water  
        Frame[] topWaterFrames = new Frame[] {
            new FrameBuilder(getSubImage(5, 0), 65)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 1), 65)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 2), 65)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 1), 65)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 0), 65)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 3), 65)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 4), 65)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 3), 65)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder topWaterTile = new MapTileBuilder(topWaterFrames)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(topWaterTile);

        // top of Lava wall 
        Frame[] LavaWallTopFrames = new Frame[] { 
                new FrameBuilder(getSubImage(0, 5), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(0, 6), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(0, 7), 20)
                        .withScale(tileScale)
                        .build(),
        };
        
        MapTileBuilder LavaWallTopTile = new MapTileBuilder(LavaWallTopFrames)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(LavaWallTopTile);

        // middle of Lava wall 
        Frame[] LavaWallMiddleFrames = new Frame[] {
                new FrameBuilder(getSubImage(1, 5), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(1, 6), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 7), 20)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder LavaWallMiddleTile = new MapTileBuilder(LavaWallMiddleFrames)
        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(LavaWallMiddleTile);

        // bottom of Lava wall 
        Frame[] LavaWallBottomFrames = new Frame[] {
                new FrameBuilder(getSubImage(2, 5), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(2, 6), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(2, 7), 20)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder LavaWallBottomTile = new MapTileBuilder(LavaWallBottomFrames)
        .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(LavaWallBottomTile);

        // top of snow wall
        Frame[] SnowWallTopFrames = new Frame[] {
                new FrameBuilder(getSubImage(3, 5), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(3, 6), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(3, 7), 20)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder SnowWallTopTile = new MapTileBuilder(SnowWallTopFrames)
        .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(SnowWallTopTile);

        // middle of snow wall
        Frame[] SnowWallMiddleFrames = new Frame[] {
                new FrameBuilder(getSubImage(4, 5), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(4, 6), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(4, 7), 20)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder SnowWallMiddleTile = new MapTileBuilder(SnowWallMiddleFrames)
        .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(SnowWallMiddleTile);

        // bottom of snow wall
        Frame[] SnowWallBottomFrames = new Frame[] {
                new FrameBuilder(getSubImage(5, 5), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(5, 6), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(5, 7), 20)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder SnowWallBottomTile = new MapTileBuilder(SnowWallBottomFrames)
        .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(SnowWallBottomTile);

        // top of brick wall
        Frame[] BrickWallTopFrames = new Frame[] {
                new FrameBuilder(getSubImage(6, 5), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(6, 6), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(6, 7), 20)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder BrickWallTopTile = new MapTileBuilder(BrickWallTopFrames)
        .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(BrickWallTopTile);

        // middle of brick wall
        Frame[] BrickWallMiddleFrames = new Frame[] {
                new FrameBuilder(getSubImage(7, 5), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(7, 6), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(7, 7), 20)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder BrickWallMiddleTile = new MapTileBuilder(BrickWallMiddleFrames)
        .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(BrickWallMiddleTile);

        // bottom of brick wall
        Frame[] brickWallBottomFrames = new Frame[] {
                new FrameBuilder(getSubImage(8, 5), 20)
                    .withScale(tileScale)
                    .build(),
                new FrameBuilder(getSubImage(8, 6), 20)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(8, 7), 20)
                        .withScale(tileScale)
                        .build(),
        };

        MapTileBuilder brickWallBottomTile = new MapTileBuilder(brickWallBottomFrames)
        .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(brickWallBottomTile);
//--------------------------------------------------------------------------------------------------------------------
        //Lava floor #1
        Frame LavaFloorOneFrame = new FrameBuilder(getSubImage(0, 8))
                .withScale(tileScale)
                .build();

        MapTileBuilder LavaFloorOneTile = new MapTileBuilder(LavaFloorOneFrame)
        .withTileType(TileType.PASSABLE);

        mapTiles.add(LavaFloorOneTile);

        //Lava floor #2
        Frame LavaFloorTwoFrame = new FrameBuilder(getSubImage(0, 9))
                .withScale(tileScale)
                .build();

        MapTileBuilder LavaFloorTwoTile = new MapTileBuilder(LavaFloorTwoFrame)
        .withTileType(TileType.PASSABLE);

        mapTiles.add(LavaFloorTwoTile);

        //Lava floor #3
        Frame LavaFloorThreeFrame = new FrameBuilder(getSubImage(1,8))
                .withScale(tileScale)
                .build();

        MapTileBuilder LavaFloorThreeTile = new MapTileBuilder(LavaFloorThreeFrame)
        .withTileType(TileType.PASSABLE);

        mapTiles.add(LavaFloorThreeTile);

        //Lava floor #4
        Frame LavaFloorFourFrame = new FrameBuilder(getSubImage(1, 9))
                .withScale(tileScale)
                .build();

        MapTileBuilder LavaFloorFourTile = new MapTileBuilder(LavaFloorFourFrame)
        .withTileType(TileType.PASSABLE);

        mapTiles.add(LavaFloorFourTile);

        //Grass floor W/flower #1
        Frame GrassFloorOneFrame = new FrameBuilder(getSubImage(2, 8))
                .withScale(tileScale)
                .build();

        MapTileBuilder GrassFloorOneTile = new MapTileBuilder(GrassFloorOneFrame)
        .withTileType(TileType.PASSABLE);

        mapTiles.add(GrassFloorOneTile);

        //Grass floor #2
        Frame GrassFloorTwoFrame = new FrameBuilder(getSubImage(2, 9))
                .withScale(tileScale)
                .build();

        MapTileBuilder GrassFloorTwoTile = new MapTileBuilder(GrassFloorTwoFrame)
        .withTileType(TileType.PASSABLE);

        mapTiles.add(GrassFloorTwoTile);

        //Grass floor #3
        Frame GrassFloorThreeFrame = new FrameBuilder(getSubImage(3, 8))
                .withScale(tileScale)
                .build();

        MapTileBuilder GrassFloorThreeTile = new MapTileBuilder(GrassFloorThreeFrame)
        .withTileType(TileType.PASSABLE);

        mapTiles.add(GrassFloorThreeTile);

        //Grass floor #4
        Frame GrassFloorFourFrame = new FrameBuilder(getSubImage(3, 9))
                .withScale(tileScale)
                .build();

        MapTileBuilder GrassFloorFourTile = new MapTileBuilder(GrassFloorFourFrame)
        .withTileType(TileType.PASSABLE);

        mapTiles.add(GrassFloorFourTile);
        
        //Snow floor #1
        Frame SnowFloorOneFrame = new FrameBuilder(getSubImage(4, 8))
                .withScale(tileScale)
                .build();

        MapTileBuilder SnowFloorOneTile = new MapTileBuilder(SnowFloorOneFrame)
        .withTileType(TileType.PASSABLE);

        mapTiles.add(SnowFloorOneTile);

        //Snow floor #2
        Frame SnowFloorTwoFrame = new FrameBuilder(getSubImage(4, 9))
                .withScale(tileScale)
                .build();

        MapTileBuilder SnowFloorTwoTile = new MapTileBuilder(SnowFloorTwoFrame)
        .withTileType(TileType.PASSABLE);

        mapTiles.add(SnowFloorTwoTile);

        //Snow floor #3
        Frame SnowFloorThreeFrame = new FrameBuilder(getSubImage(5, 8))
                .withScale(tileScale)
                .build();

        MapTileBuilder SnowFloorThreeTile = new MapTileBuilder(SnowFloorThreeFrame)
        .withTileType(TileType.PASSABLE);

        mapTiles.add(SnowFloorThreeTile);

        //Snow floor #4
        Frame SnowFloorFourFrame = new FrameBuilder(getSubImage(5, 9))
                .withScale(tileScale)
                .build();

        MapTileBuilder SnowFloorFourTile = new MapTileBuilder(SnowFloorFourFrame)
        .withTileType(TileType.PASSABLE);

        mapTiles.add(SnowFloorFourTile);

        return mapTiles;
    }
}
