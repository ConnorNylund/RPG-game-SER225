package Scripts.TestMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;

// script for talking to walrus npc
// checkout the documentation website for a detailed guide on how this script works
public class PirateBunnyScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new NPCFacePlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasTalkedToPirate", false));
                addScriptAction(new TextboxScriptAction() {{
                    addText("Hi Bunny, You have Wondered into the amazing land\n of Sector Survival!");
                    addText("Your goal is to defeat all the invasive species\n who seek to destroy you and your crops");
                    addText("Located above us there is a shop \nwhere you can spend your hard earned currency");
                    addText("At the shop you can customize your arsenal\n and select your choice of steroids ");

                }});
                addScriptAction(new ChangeFlagScriptAction("hasTalkedToPirate", true));
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasTalkedToPirate", true));
                addScriptAction(new TextboxScriptAction("I sure love doing walrus things!"));
            }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
