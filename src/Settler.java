import javax.lang.model.type.NullType;
import java.util.ArrayList;

public class Settler extends Entity{


    private ArrayList<Material> inventory;
    private ArrayList<Teleport> teleportlist;


    public Settler() {
        inventory= new ArrayList<Material>();
        teleportlist= new ArrayList<Teleport>();
    }

    @Override
    public void Blow() {
        this.Die();
    }

    @Override
    public void Die(){
        getAsteroid().RemoveEntity(this);
        //aszteroidarol levenni? és ksizedni a settler aszteroidáját?
    }

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
                coal++;
            }
            if(m instanceof Uranium){
                uranium++;
            }
        }

        //Ha van elég csükkentjuk es letrehozzuk a robotot az aszteroidan
        if(iron>=1&&coal>=1&&uranium>=1){
            for (Material m : inventory)
            {
                boolean deleteiron=true;
                boolean deletecoal=true;
                boolean deleteuranium=true;

                //Toroljuk a szukseges anyagokat
                if(m instanceof Iron && deleteiron){
                    inventory.remove(m);
                    deleteiron=false;
                }
                if(m instanceof Coal && deletecoal){
                    inventory.remove(m);
                    deletecoal=false;
                }
                if(m instanceof Uranium && deleteuranium){
                    inventory.remove(m);
                    deleteuranium=false;
                }
            }

            Robot r= new Robot();
            r.setAsteroid(this.getAsteroid());
            this.getAsteroid().Accept(r);
        }

    }
    public void MakeTeleport(){
        int iron=0;
        int ice=0;
        int uranium=0;  //Megnézzuk van e elég anyag az építéshez
        for (Material m : inventory)
        {
            if(m instanceof Iron){
                iron++;
            }
            if(m instanceof Ice){
                ice++;
            }
            if(m instanceof Uranium){
                uranium++;
            }
        }
        //Ha van elég csükkentjuk es letrehozzuk a teleportot és hozzáadjuk a teleportlisthez
        if(iron>=2&&ice>=1&&uranium>=1&&teleportlist.isEmpty()){
                for (Material m : inventory)
                {
                    int deleteiron=0; //kettot kell belole torolni ezert ezt igy csinalom
                    boolean deleteice=true;
                    boolean deleteuranium=true;

                    if(m instanceof Iron && deleteiron<2){
                        inventory.remove(m);
                        deleteiron++;
                    }
                    if(m instanceof Coal && deleteice){
                        inventory.remove(m);
                        deleteice=false;
                    }
                    if(m instanceof Uranium && deleteuranium){
                        inventory.remove(m);
                        deleteuranium=false;
                    }
                }
            Teleport t= new Teleport();
            this.teleportlist.add(t);
        }
    }

    public void Mine(){
        if(getAsteroid().getCrustThickness()==0 && inventory.size()<=10){
            Material i=getAsteroid().MinedBy();
            inventory.add(i);
            getAsteroid().CheckBase();
        }
    }

    public void PlaceMaterial(){
        if(getAsteroid().getMaterial()==null && getAsteroid().getCrustThickness() == 0){
            //mit teszunk le?
            getAsteroid().AddMaterial(inventory.get(0));
            inventory.remove(0);
        }
    }

    public void PlaceTeleport(){
        getAsteroid().BuildTeleport(teleportlist.get(0)); //lehetne csak egy sima teleport
        //hol removeoljuk?, egyiket leteszi és figyeljuk a másikat mikor teszi?
        if(teleportlist.get(0).getAsteroids().size()==2){
            teleportlist.remove(0);
        }
    }

    public ArrayList<Material> getInventory() {
        return inventory;
    }
}
