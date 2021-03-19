import java.util.ArrayList;

public class Settler extends Entity{

    private ArrayList<Material> inventory;
    private ArrayList<Teleport> teleportlist;

    @Override
    public void Blow() {
        this.Die();
    }

    @Override
    public void Die(){
        //aszteroidarol levenni? és ksizedni a settler aszteroidáját?
    }

    //kell hogy megnézzuk van e elég
    public boolean CanBuildRobot(){}
    public boolean CanBuildTeleport(){}
    public void BuildRobot(){
        int iron=0;
        int coal=0;
        int uranium=0;  //Megnézzuk van e elég anyag az építéshez
        for (Material m : inventory)
        {
           if(m instanceof Iron){
               iron++;
           }
            if(m instanceof Coal){
                coal+;
            }
            if(m instanceof Uranium){
                uranium++;
            }
        }

    }
    public void MakeTeleport(){}
    public void Mine(){}
    public void PlaceMaterial(){}
    public void PlaceTeleport(){}

    public ArrayList<Material> getInventory() {
        return inventory;
    }
}
