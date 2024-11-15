package Scripts.TestMap;

import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import GameObject.Frame;
import Level.*;
import Maps.TestMap;
import ScriptActions.*;
import Maps.TestMap;

import Utils.Direction;
import Utils.Point;
import Utils.Visibility;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class FisherScript extends Script {
   

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        
        

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasTalkedToPirate", false));
                addRequirement(new FlagRequirement("hasTalkedToFisher", false));

                addScriptAction(new WaitScriptAction(70));
                addScriptAction(new NPCFacePlayerScriptAction());
                addScriptAction(new TextboxScriptAction () {{
                    addText("What a Wonderful day, I belive that Pirate\n over there has something to tell you");
                    
                }});
                
                addScriptAction(new ChangeFlagScriptAction("hasTalkedToFisher", false));
            }});
        }});


        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasTalkedToPirate", true));
                addRequirement(new FlagRequirement("hasTalkedToFisher", false));

                addScriptAction(new WaitScriptAction(70));
                addScriptAction(new NPCFacePlayerScriptAction());
                addScriptAction(new DestroyGrassWallsScriptAction()); 
                addScriptAction(new TextboxScriptAction () {{
                    addText("Now that your caught up, I need your help");
                    addText("In the field on your right there are swarms of enemies\nwho have taken over my crops?");
                    addText("Now, Go if you dare and save my crops.");
                    
                    
                }});
                
                addScriptAction(new ChangeFlagScriptAction("hasTalkedToFisher", true));
            }});
        }});


        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}

