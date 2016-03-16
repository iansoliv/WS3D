/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import com.jme.image.Texture;
import com.jme.math.Quaternion;
import com.jme.scene.Node;
import com.jme.scene.state.MaterialState;
import com.jme.scene.state.TextureState;
import com.jme.util.TextureManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Constants;

/**
 * Currently discontinued. Delivery spot is not a Thing in current version.
 * @author eccastro
 */
public class DeliverySpot  extends Thing{

    public DeliverySpot(){ //Savable matters only

    }
    public DeliverySpot(double x, double y, Environment ev, MaterialState ms, TextureState ts) {
        super(x,y,ev);
        try {
            //imADeliverySot = true;
            x1 = x;
            y1 = y;
            x2 = x;
            y2 = y;
            sf = new ThingShapeFactory("images/chest2.3DS", this);
            this.ts = ts;
            this.ms = ms;
            this.category = Constants.categoryDeliverySPOT;
 //           shape = sf.getNode(0.02f);
            shape = sf.getNode(0.05f);

            shape.setRenderState(ms);
            shape.setRenderState(ev.ls);

            //setMyTexture("images/texture8.jpg");
            shape.updateRenderState();

            setMaterial(new Material3D(1.0, 0, ms));
            setTexture("images/texture10.jpg");
            affordances = new ArrayList<Integer>();
            affordances.add(Constants.Affordance__VIEWABLE);
        } catch (IOException ex) {
            System.out.println("!!!!!DeliverySpot: Erro ! ");
            ex.printStackTrace();
        }

        
    }

    @Override
    public void moveTo(double dx, double dy) {
        throw new UnsupportedOperationException("Does not apply to this object!");
    }

    @Override
    public void initPlace() {
        //
    }
    @Override
    public Node myLocalTransformations(Node modelw) {
        modelw.setLocalTranslation(0, 3f, 0);
        modelw.getLocalRotation().fromAngles(-(float) (Math.PI / 2), (float) (Math.PI / 4), 0f);

        return modelw;
    }
    @Override
 public void setID(Long id, Environment e){
      this.ID = id;
      String name = Constants.DELIVERY_SPOT_PREFIX;
      this.shape.setName(name.concat(id.toString()));
      //System.out.println("====  My name is "+this.shape.getName());
      e.thingMap.put(myName, this);
  }

    @Override
    public Knapsack putMeInKnapsack(Knapsack sack) {
        throw new UnsupportedOperationException("Makes no sense for my type of Thing!!!");
    }

}
