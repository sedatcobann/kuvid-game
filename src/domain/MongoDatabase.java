package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
//import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.result.DeleteResult;
//import com.mongodb.MongoClientSettings;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import static com.mongodb.client.model.Filters.eq;

public class MongoDatabase implements DatabaseAdapter {

	private Controller controller;
	public MongoDatabase(Controller controller) {
		this.controller = controller;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean save(String username) {
		String[] types = {"alpha","beta","gamma","sigma"};
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.	
		MongoClient mongoClient = MongoClients.create("mongodb+srv://comp302_user:comp302_password@sandbox.v2mqr.mongodb.net/"); // uri connection to the server
		com.mongodb.client.MongoDatabase database = mongoClient.getDatabase("Comp302"); // selecting the database 
		MongoCollection<Document> collection = ((com.mongodb.client.MongoDatabase) database).getCollection("FastandFuriousKuvidCollection"); // collection 

		Document doc = new Document();
		doc.append("username", username);
		//Player Information
		double health = controller.getHealth();
		double time = controller.getPlayerTime();
		double score = controller.getScore();
		Document playerDoc = new Document();
		playerDoc.append("PlayerHealth", health);
		playerDoc.append("PlayerTime", time);
		playerDoc.append("PlayerScore", score);
		doc.append("Player", playerDoc);

		//Inventory Information

		HashMap<Integer, Stack<AtomAbstract>> AtomContainer =  controller.getAtomContainer(); 

		Document inventory = new Document();
		Document atomContainerDoc = new Document();
		for(int i = 0; i<AtomContainer.size(); i++) {
			atomContainerDoc.append(types[i], AtomContainer.get(i).size());
		}
		inventory.append("AtomContainer", atomContainerDoc);


		HashMap<Integer, Integer> shieldContainer = controller.getShieldContainer(); 
		Document shieldContainerDoc = new Document();
		for (int i=0; i<shieldContainer.size(); i++) {
			shieldContainerDoc.append(String.valueOf(i), shieldContainer.get(i));
		}
		inventory.append("Shield Container", shieldContainerDoc);


		HashMap<Integer,Stack<ShootingPowerUp>> powerupContainer = controller.getPowerupContainer();
		Document powerupContainerDoc = new Document();
		for(int i = 0; i<powerupContainer.size(); i++) {
			powerupContainerDoc.append(types[i], powerupContainer.get(i).size());
		}
		inventory.append("PowerupContainer", powerupContainerDoc);




		doc.append("Inventory", inventory);


		//Shooter Information
		Document shooterDoc = new Document();
		int shooterVelocity = controller.getShooterVelocity(); 
		int shooterOriginXPos = controller.getShooterXPos(); 
		int shooterOriginYPos = controller.getShooterYPos(); 
		double shooterAngle = controller.getShooterAngle(); 
		double[][] shooterCoordinations = controller.getShooterCoordinations(); 
		int atomToShoot = controller.getAtomToShoot(); 
		int shooterHeight = controller.getShooterHeight(); 
		int shooterWidth = controller.getShooterWidth(); 
		int powerupToShoot = controller.getPowerupToShoot();
		shooterDoc.append("shooterVelocity", shooterVelocity);
		shooterDoc.append("shooterOriginXPos", shooterOriginXPos);
		shooterDoc.append("shooterOriginYPos", shooterOriginYPos);
		shooterDoc.append("shooterAngle", shooterAngle); 

		Document shooterCoordinationsDoc = new Document();
		for(int i=0; i<shooterCoordinations.length;i++) {
			Document coordDoc = new Document();
			coordDoc.append("x"+String.valueOf(i), shooterCoordinations[i][0]).append("y"+String.valueOf(i), shooterCoordinations[i][1]);
			shooterCoordinationsDoc.append(String.valueOf(i), coordDoc);
		}


		shooterDoc.append("shooterCoordinations",shooterCoordinationsDoc); 
		shooterDoc.append("atomToShoot", atomToShoot);
		shooterDoc.append("powerupToShoot", powerupToShoot);
		shooterDoc.append("shooterHeight", shooterHeight); 
		shooterDoc.append("shooterWidth",shooterWidth);
		doc.append("Shooter", shooterDoc);

		//GameEnvironment Information
		Document gameEnvironmentDoc = new Document();
		int gameWidth = controller.getGameWidth();
		int gameHeight = controller.getGameHeight();
		List<Integer> moleculeStructure = controller.getMoleculeStructure();
		List<Integer> moleculeMov = controller.getMoleculeMov();
		HashMap<Integer,Integer> objectNumList = controller.getObjectNumList();

		ArrayList<List <Moveable>> AtomList = controller.getAtomList();

		Document atomListDoc = new Document();
		for(int i = 0; i<AtomList.size(); i++) {
			Document type = new Document();
			List<Moveable> typeAtom = AtomList.get(i);
			for(int k=0; k<typeAtom.size(); k++) {
				Document featuresAtom = new Document();
				AtomAbstract atom = (AtomAbstract) typeAtom.get(k);
				featuresAtom.append("xLoc", atom.getxLoc()).append("yLoc",atom.getyLoc()).append("height",atom.getHeight()).append("width",atom.getWidth()).append("dx",atom.getDx()).append("dy",atom.getDy()).append("Neutron", atom.getNeutron()).append("Proton", atom.getProton()).append("Stability", atom.getStability()).append("Efficiency", atom.getEfficiency());
				type.append(String.valueOf(k), featuresAtom);

			}
			atomListDoc.append(types[i], type);
		}
		gameEnvironmentDoc.append("AtomList", atomListDoc);

		ArrayList<DroppingPowerUp> DroppingPowerUpList = controller.getDroppingPowerUpList();
		ArrayList<ShootingPowerUp> ShootingPowerUpList = controller.getShootingPowerUpList();
		ArrayList<ReactionBlocker> ReactionBlockerList = controller.getReactionBlockerList();
		ArrayList<List <Moveable>> MoleculeList = controller.getMoleculeList();
		Document moleculeListDoc = new Document();
		for(int i = 0; i<MoleculeList.size(); i++) {
			Document type = new Document();
			List<Moveable> typeMolecule = MoleculeList.get(i);
			for(int k=0; k<typeMolecule.size(); k++) {
				Document featuresMolecule = new Document();
				Moveable molecule = typeMolecule.get(k);
				featuresMolecule.append("xLoc", molecule.getxLoc()).append("yLoc",molecule.getyLoc()).append("height",molecule.getHeight()).append("width",molecule.getWidth()).append("dx",molecule.getDx()).append("dy",molecule.getDy());
				type.append(String.valueOf(k), featuresMolecule);
			}
			moleculeListDoc.append(types[i], type);
		}
		gameEnvironmentDoc.append("MoleculeList", moleculeListDoc);

		Document moleculeStructureDoc = new Document();
		for (int i=0; i<moleculeStructure.size(); i++) {
			moleculeStructureDoc.append(String.valueOf(i), moleculeStructure.get(i));
		}

		gameEnvironmentDoc.append("moleculeStructure", moleculeStructureDoc);


		Document moleculeMovDoc = new Document();
		for (int i=0; i<moleculeMov.size(); i++) {
			moleculeMovDoc.append(String.valueOf(i), moleculeMov.get(i));
		}

		gameEnvironmentDoc.append("moleculeMov", moleculeMov);

		Document objectNumListDoc = new Document();
		for (int i=0; i<objectNumList.size(); i++) {
			objectNumListDoc.append(String.valueOf(i), objectNumList.get(i));
		}

		gameEnvironmentDoc.append("objectNumList", objectNumListDoc);

		Document DroppingPowerUpListDoc = new Document();
		for (int i=0; i<DroppingPowerUpList.size(); i++) {
			DroppingPowerUp powerup = DroppingPowerUpList.get(i);
			Document featuresDroppingPowerup = new Document();
			featuresDroppingPowerup.append("xLoc", powerup.getxLoc()).append("yLoc",powerup.getyLoc()).append("height",powerup.getHeight()).append("width",powerup.getWidth()).append("dx",powerup.getDx()).append("dy",powerup.getDy()).append("Type", powerup.getType());
			DroppingPowerUpListDoc.append(String.valueOf(i), featuresDroppingPowerup);
		}


		gameEnvironmentDoc.append("DroppingPowerUpList", DroppingPowerUpListDoc);

		Document ShootingPowerUpListDoc = new Document();
		for (int i=0; i<ShootingPowerUpList.size(); i++) {
			ShootingPowerUp powerup = ShootingPowerUpList.get(i);
			Document featuresShootingPowerUp = new Document();
			featuresShootingPowerUp.append("xLoc", powerup.getxLoc()).append("yLoc",powerup.getyLoc()).append("height",powerup.getHeight()).append("width",powerup.getWidth()).append("dx",powerup.getDx()).append("dy",powerup.getDy()).append("Type", powerup.getType());
			ShootingPowerUpListDoc.append(String.valueOf(i), featuresShootingPowerUp);
		}


		gameEnvironmentDoc.append("ShootingPowerUpList", ShootingPowerUpListDoc);


		Document ReactionBlockerListDoc = new Document();
		for (int i=0; i<ReactionBlockerList.size(); i++) {
			ReactionBlocker rb = ReactionBlockerList.get(i);
			Document featuresReactionBlocker = new Document();
			featuresReactionBlocker.append("xLoc", rb.getxLoc()).append("yLoc",rb.getyLoc()).append("height",rb.getHeight()).append("width",rb.getWidth()).append("dx",rb.getDx()).append("dy",rb.getDy()).append("Type", rb.getType());
			ReactionBlockerListDoc.append(String.valueOf(i), featuresReactionBlocker);
		}


		gameEnvironmentDoc.append("ReactionBlockerList",ReactionBlockerListDoc);

		int L = controller.getL();	
		gameEnvironmentDoc.append("gameWidth", gameWidth);
		gameEnvironmentDoc.append("gameHeight", gameHeight);
		gameEnvironmentDoc.append("L", L);
		int difficultyLevel = controller.getDifficultyLevel();
		gameEnvironmentDoc.append("difficultyLevel", difficultyLevel);
		doc.append("GameEnvironment", gameEnvironmentDoc);
		collection.insertOne(doc);


		return true;
	}

	@Override
	public boolean load(String username) {
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.	
		MongoClient mongoClient = MongoClients.create("mongodb+srv://comp302_user:comp302_password@sandbox.v2mqr.mongodb.net/"); // uri connection to the server
		com.mongodb.client.MongoDatabase database = mongoClient.getDatabase("Comp302"); // selecting the database 
		MongoCollection<Document> collection = ((com.mongodb.client.MongoDatabase) database).getCollection("FastandFuriousKuvidCollection"); // collection 
		// finding the document: 1st way
		Document my_doc = collection.find(eq("username", username)).first(); 
		if(my_doc != null) {
			//Player Info
			Document playerDoc = (Document) my_doc.get("Player");
			controller.setPlayerHealth(playerDoc.getDouble("PlayerHealth"));
			controller.setPlayerScore(playerDoc.getDouble("PlayerScore"));
			controller.setPlayerTime(playerDoc.getDouble("PlayerTime"));


			//Inventory Info
			Document invDoc = (Document) my_doc.get("Inventory");
			Document atomContainer = (Document) invDoc.get("AtomContainer");
			int alphaAtom = atomContainer.getInteger("alpha");
			int betaAtom = atomContainer.getInteger("beta");
			int gammaAtom = atomContainer.getInteger("gamma");
			int sigmaAtom = atomContainer.getInteger("sigma");
			List<Integer> atomNumberList = new ArrayList<>(Arrays.asList(alphaAtom, betaAtom, gammaAtom, sigmaAtom));

			Document shieldContainer = (Document) invDoc.get("Shield Container");
			int eta = shieldContainer.getInteger("0");
			int lota = shieldContainer.getInteger("1");
			int theta = shieldContainer.getInteger("2");
			int zeta = shieldContainer.getInteger("3");
			List<Integer> shieldNumberList = new ArrayList<>(Arrays.asList(eta, lota, theta, zeta));
			controller.initializeInventoryWhenLoading(atomNumberList, shieldNumberList);

			Document powerupContainer = (Document) invDoc.get("PowerupContainer");
			int alphaPW = powerupContainer.getInteger("alpha");
			int betaPW = powerupContainer.getInteger("beta");
			int gammaPW = powerupContainer.getInteger("gamma");
			int sigmaPW = powerupContainer.getInteger("sigma");
			List<Integer> powerupNumberList = new ArrayList<>(Arrays.asList(alphaPW, betaPW, gammaPW, sigmaPW));
			controller.initializeShootingPowerupWhenLoading(powerupNumberList);

			//Shooter Info
			Document shooterDoc = (Document) my_doc.get("Shooter");
			int vel = shooterDoc.getInteger("shooterVelocity");
			int orX = shooterDoc.getInteger("shooterOriginXPos");
			int orY = shooterDoc.getInteger("shooterOriginYPos");
			double angle = shooterDoc.getDouble("shooterAngle");
			int atomToShoot = shooterDoc.getInteger("atomToShoot");
			int pwToShoot = shooterDoc.getInteger("powerupToShoot");
			Document shootCordDoc = (Document) shooterDoc.get("shooterCoordinations");
			Document x0Doc = (Document) shootCordDoc.get("0");
			Document x1Doc = (Document) shootCordDoc.get("1");
			Document x2Doc = (Document) shootCordDoc.get("2");
			Document x3Doc = (Document) shootCordDoc.get("3");
			List<Double> coord = new ArrayList<>(Arrays.asList(x0Doc.getDouble("x0"), x0Doc.getDouble("y0"), x1Doc.getDouble("x1"), x1Doc.getDouble("y1"),x2Doc.getDouble("x2"),x2Doc.getDouble("y2"),x3Doc.getDouble("x3"),x3Doc.getDouble("y3")));
			controller.initShooterWhenLoading(vel, orX, orY, angle, coord, atomToShoot, pwToShoot);

			//Game Environment
			Document envDoc = (Document) my_doc.get("GameEnvironment");
			int L = envDoc.getInteger("L");
			int gameWidth = envDoc.getInteger("gameWidth");
			int gameHeight = envDoc.getInteger("gameHeight");
			int difLevel = envDoc.getInteger("difficultyLevel");
			controller.setEnvironmentFeatures(L,gameWidth,gameHeight,difLevel);
			Document atomListDoc = (Document) envDoc.get("AtomList");
			Document alphaAtomListDoc = (Document) atomListDoc.get("alpha");
			Document betaAtomListDoc = (Document) atomListDoc.get("beta");
			Document gammaAtomListDoc = (Document) atomListDoc.get("gamma");
			Document sigmaAtomListDoc = (Document) atomListDoc.get("sigma");
			List<ArrayList<Double>> alphaAtoms = new ArrayList<ArrayList<Double>>();
			Document aAtom = (Document) alphaAtomListDoc.get("0");
			int i = 0;
			while(aAtom != null) {
				ArrayList<Double> aInfoList = new ArrayList<Double>();
				aInfoList.add(Double.valueOf(aAtom.getInteger("xLoc")));
				aInfoList.add(Double.valueOf(aAtom.getInteger("yLoc")));
				aInfoList.add(aAtom.getDouble("height"));
				aInfoList.add(aAtom.getDouble("width"));
				aInfoList.add(aAtom.getDouble("dx"));
				aInfoList.add(aAtom.getDouble("dy"));
				aInfoList.add(Double.valueOf(aAtom.getInteger("Neutron")));
				aInfoList.add(Double.valueOf(aAtom.getInteger("Proton")));
				aInfoList.add(aAtom.getDouble("Stability"));
				aInfoList.add(aAtom.getDouble("Efficiency"));
				alphaAtoms.add(aInfoList);
				i++;
				aAtom = (Document) alphaAtomListDoc.get(String.valueOf(i));
			}

			List<ArrayList<Double>> betaAtoms = new ArrayList<ArrayList<Double>>();
			Document bAtom = (Document) betaAtomListDoc.get("0");
			i = 0;
			while(bAtom != null) {
				ArrayList<Double> bInfoList = new ArrayList<Double>();
				bInfoList.add(Double.valueOf(bAtom.getInteger("xLoc")));
				bInfoList.add(Double.valueOf(bAtom.getInteger("yLoc")));
				bInfoList.add(bAtom.getDouble("height"));
				bInfoList.add(bAtom.getDouble("width"));
				bInfoList.add(bAtom.getDouble("dx"));
				bInfoList.add(bAtom.getDouble("dy"));
				bInfoList.add(Double.valueOf(bAtom.getInteger("Neutron")));
				bInfoList.add(Double.valueOf(bAtom.getInteger("Proton")));
				bInfoList.add(bAtom.getDouble("Stability"));
				bInfoList.add(bAtom.getDouble("Efficiency"));
				betaAtoms.add(bInfoList);
				i++;
				bAtom = (Document) betaAtomListDoc.get(String.valueOf(i));
			}

			List<ArrayList<Double>> gammaAtoms = new ArrayList<ArrayList<Double>>();
			Document gAtom = (Document) gammaAtomListDoc.get("0");
			i = 0;
			while(gAtom != null) {
				ArrayList<Double> gInfoList = new ArrayList<Double>();
				gInfoList.add(Double.valueOf(gAtom.getInteger("xLoc")));
				gInfoList.add(Double.valueOf(gAtom.getInteger("yLoc")));
				gInfoList.add(gAtom.getDouble("height"));
				gInfoList.add(gAtom.getDouble("width"));
				gInfoList.add(gAtom.getDouble("dx"));
				gInfoList.add(gAtom.getDouble("dy"));
				gInfoList.add(Double.valueOf(gAtom.getInteger("Neutron")));
				gInfoList.add(Double.valueOf(gAtom.getInteger("Proton")));
				gInfoList.add(gAtom.getDouble("Stability"));
				gInfoList.add(gAtom.getDouble("Efficiency"));
				gammaAtoms.add(gInfoList);
				i++;
				gAtom = (Document) gammaAtomListDoc.get(String.valueOf(i));
			}

			List<ArrayList<Double>> sigmaAtoms = new ArrayList<ArrayList<Double>>();
			Document sAtom = (Document) sigmaAtomListDoc.get("0");
			i = 0;
			while(sAtom != null) {
				ArrayList<Double> sInfoList = new ArrayList<Double>();
				sInfoList.add(Double.valueOf(sAtom.getInteger("xLoc")));
				sInfoList.add(Double.valueOf(sAtom.getInteger("yLoc")));
				sInfoList.add(sAtom.getDouble("height"));
				sInfoList.add(sAtom.getDouble("width"));
				sInfoList.add(sAtom.getDouble("dx"));
				sInfoList.add(sAtom.getDouble("dy"));
				sInfoList.add(Double.valueOf(sAtom.getInteger("Neutron")));
				sInfoList.add(Double.valueOf(sAtom.getInteger("Proton")));
				sInfoList.add(sAtom.getDouble("Stability"));
				sInfoList.add(sAtom.getDouble("Efficiency"));
				sigmaAtoms.add(sInfoList);
				i++;
				sAtom = (Document) sigmaAtomListDoc.get(String.valueOf(i));
			}

			controller.initializingAtomsInEnvironmentWhenLoading(alphaAtoms, betaAtoms, gammaAtoms, sigmaAtoms);

			Document moleculeStructureDoc = (Document) envDoc.get("moleculeStructure");
			List<Integer> moleculeStructure = new ArrayList<>(Arrays.asList(moleculeStructureDoc.getInteger("0"),moleculeStructureDoc.getInteger("1")));
			controller.setEnvironmentMoleculeStructure(moleculeStructure);

			Document moleculeMovDoc = (Document) envDoc.get("moleculeMovDoc");
			if(moleculeMovDoc != null) {
				List<Integer> moleculeMov = new ArrayList<>(Arrays.asList(moleculeMovDoc.getInteger("0"),moleculeMovDoc.getInteger("1")));
				controller.setEnvironmentMoleculeMov(moleculeMov);
			}



			Document moleculeListDoc = (Document) envDoc.get("MoleculeList");
			Document alphaMoleculeListDoc = (Document) moleculeListDoc.get("alpha");
			Document betaMoleculeListDoc = (Document) moleculeListDoc.get("beta");
			Document gammaMoleculeListDoc = (Document) moleculeListDoc.get("gamma");
			Document sigmaMoleculeListDoc = (Document) moleculeListDoc.get("sigma");
			List<ArrayList<Double>> alphaMolecules = new ArrayList<ArrayList<Double>>();
			Document aMolecule = (Document) alphaMoleculeListDoc.get("0");
			i = 0;
			while(aMolecule != null) {
				ArrayList<Double> aInfoList = new ArrayList<Double>();
				aInfoList.add(Double.valueOf(aMolecule.getInteger("xLoc")));
				aInfoList.add(Double.valueOf(aMolecule.getInteger("yLoc")));
				aInfoList.add(Double.valueOf(aMolecule.getDouble("height")));
				aInfoList.add(Double.valueOf(aMolecule.getDouble("width")));
				aInfoList.add(Double.valueOf(aMolecule.getDouble("dx")));
				aInfoList.add(Double.valueOf(aMolecule.getDouble("dy")));
				alphaMolecules.add(aInfoList);
				i++;
				aMolecule = (Document) alphaMoleculeListDoc.get(String.valueOf(i));
			}

			List<ArrayList<Double>> betaMolecules = new ArrayList<ArrayList<Double>>();
			Document bMolecule = (Document) betaMoleculeListDoc.get("0");
			i = 0;
			while(bMolecule != null) {
				ArrayList<Double> bInfoList = new ArrayList<Double>();
				bInfoList.add(Double.valueOf(bMolecule.getInteger("xLoc")));
				bInfoList.add(Double.valueOf(bMolecule.getInteger("yLoc")));
				bInfoList.add(Double.valueOf(bMolecule.getDouble("height")));
				bInfoList.add(Double.valueOf(bMolecule.getDouble("width")));
				bInfoList.add(Double.valueOf(bMolecule.getDouble("dx")));
				bInfoList.add(Double.valueOf(bMolecule.getDouble("dy")));
				betaMolecules.add(bInfoList);
				i++;
				bMolecule = (Document) betaMoleculeListDoc.get(String.valueOf(i));
			}

			List<ArrayList<Double>> gammaMolecules = new ArrayList<ArrayList<Double>>();
			Document gMolecule = (Document) gammaMoleculeListDoc.get("0");
			i = 0;
			while(gMolecule != null) {
				ArrayList<Double> gInfoList = new ArrayList<Double>();
				gInfoList.add(Double.valueOf(gMolecule.getInteger("xLoc")));
				gInfoList.add(Double.valueOf(gMolecule.getInteger("yLoc")));
				gInfoList.add(Double.valueOf(gMolecule.getDouble("height")));
				gInfoList.add(Double.valueOf(gMolecule.getDouble("width")));
				gInfoList.add(Double.valueOf(gMolecule.getDouble("dx")));
				gInfoList.add(Double.valueOf(gMolecule.getDouble("dy")));
				gammaMolecules.add(gInfoList);
				i++;
				gMolecule = (Document) gammaMoleculeListDoc.get(String.valueOf(i));
			}

			List<ArrayList<Double>> sigmaMolecules = new ArrayList<ArrayList<Double>>();
			Document sMolecule = (Document) sigmaMoleculeListDoc.get("0");
			i = 0;
			while(sMolecule != null) {
				ArrayList<Double> sInfoList = new ArrayList<Double>();
				sInfoList.add(Double.valueOf(sMolecule.getInteger("xLoc")));
				sInfoList.add(Double.valueOf(sMolecule.getInteger("yLoc")));
				sInfoList.add(Double.valueOf(sMolecule.getDouble("height")));
				sInfoList.add(Double.valueOf(sMolecule.getDouble("width")));
				sInfoList.add(Double.valueOf(sMolecule.getDouble("dx")));
				sInfoList.add(Double.valueOf(sMolecule.getDouble("dy")));
				sigmaMolecules.add(sInfoList);
				i++;
				sMolecule = (Document) sigmaMoleculeListDoc.get(String.valueOf(i));
			}

			controller.initializingMoleculesInEnvironmentWhenLoading(alphaMolecules, betaMolecules, gammaMolecules, sigmaMolecules);


			Document dpListDoc = (Document) envDoc.get("DroppingPowerUpList");
			List<ArrayList<Double>> dpList = new ArrayList<ArrayList<Double>>();
			Document dp = (Document) dpListDoc.get("0");
			i = 0;
			while(dp != null) {
				ArrayList<Double> aInfoList = new ArrayList<Double>();
				aInfoList.add(Double.valueOf(dp.getInteger("xLoc")));
				aInfoList.add(Double.valueOf(dp.getInteger("yLoc")));
				aInfoList.add(Double.valueOf(dp.getDouble("height")));
				aInfoList.add(Double.valueOf(dp.getDouble("width")));
				aInfoList.add(Double.valueOf(dp.getDouble("dx")));
				aInfoList.add(Double.valueOf(dp.getDouble("dy")));
				aInfoList.add(Double.valueOf(dp.getInteger("Type")));
				dpList.add(aInfoList);
				i++;
				dp = (Document) dpListDoc.get(String.valueOf(i));
			}



			Document rbListDoc = (Document) envDoc.get("ReactionBlockerList");
			List<ArrayList<Double>> rbList = new ArrayList<ArrayList<Double>>();
			Document rb = (Document) rbListDoc.get("0");
			i = 0;
			while(rb != null) {
				ArrayList<Double> aInfoList = new ArrayList<Double>();
				aInfoList.add(Double.valueOf(rb.getInteger("xLoc")));
				aInfoList.add(Double.valueOf(rb.getInteger("yLoc")));
				aInfoList.add(Double.valueOf(rb.getDouble("height")));
				aInfoList.add(Double.valueOf(rb.getDouble("width")));
				aInfoList.add(Double.valueOf(rb.getDouble("dx")));
				aInfoList.add(Double.valueOf(rb.getDouble("dy")));
				aInfoList.add(Double.valueOf(rb.getInteger("Type")));
				rbList.add(aInfoList);
				i++;
				rb = (Document) rbListDoc.get(String.valueOf(i));
			}

			Document spListDoc = (Document) envDoc.get("ShootingPowerUpList");
			List<ArrayList<Double>> spList = new ArrayList<ArrayList<Double>>();
			Document sp = (Document) spListDoc.get("0");
			i = 0;
			while(sp != null) {
				ArrayList<Double> aInfoList = new ArrayList<Double>();
				aInfoList.add(Double.valueOf(sp.getInteger("xLoc")));
				aInfoList.add(Double.valueOf(sp.getInteger("yLoc")));
				aInfoList.add(Double.valueOf(sp.getDouble("height")));
				aInfoList.add(Double.valueOf(sp.getDouble("width")));
				aInfoList.add(Double.valueOf(sp.getDouble("dx")));
				aInfoList.add(Double.valueOf(sp.getDouble("dy")));
				aInfoList.add(Double.valueOf(sp.getInteger("Type")));
				spList.add(aInfoList);
				i++;
				sp = (Document) spListDoc.get(String.valueOf(i));
			}


			controller.initializingDroppingPowerupInEnvironmentWhenLoading(dpList, rbList, spList);
			
			Document objectNumDoc = (Document) envDoc.get("objectNumList");
			ArrayList<Integer> objectNumList = new ArrayList<Integer>();
			for (int k = 0; k<objectNumDoc.size(); k++) {
				objectNumList.add(objectNumDoc.getInteger(String.valueOf(k)));
			}
			
			controller.setObjectNumList(objectNumList);
			Bson filter = eq("username", username);
			DeleteResult result = collection.deleteOne(filter);
			return true;
		}else {
			return false;
		}
		//System.out.println(my_doc.toString()); // printing whole document 
		//System.out.println(my_doc.get("username")); // pringint only the username

	}

}
