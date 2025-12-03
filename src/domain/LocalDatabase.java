package domain;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;


public class LocalDatabase implements DatabaseAdapter {
	private Controller controller;

	/**
	 * @param controller
	 */
	public LocalDatabase(Controller controller) {
		this.controller = controller;
	}

	@Override
	public boolean save(String username) {
		// TODO Auto-generated method stub
		try {
			File saveFileDir=new File("save_files");
			saveFileDir.mkdirs();
			File saveFile=new File(saveFileDir,username+".txt");
			saveFile.createNewFile();
			BufferedWriter writer=new BufferedWriter(new FileWriter(saveFile));

			//Player Information

			double health = controller.getHealth();
			double time = controller.getPlayerTime();
			double score = controller.getScore();
			writer.write(health+" " + time +" "+ score);
			writer.write("\n");
			writer.write("\n");
			writer.flush();

			//Inventory Information
			HashMap<Integer, Stack<AtomAbstract>> AtomContainer =  controller.getAtomContainer(); 

			for(int i = 0; i<AtomContainer.size(); i++) {
				writer.write(AtomContainer.get(i).size()+ "\t");
			}
			writer.write("\n");
			writer.flush();


			HashMap<Integer, Integer> shieldContainer = controller.getShieldContainer(); 

			for (int i=0; i<shieldContainer.size(); i++) {
				writer.write(shieldContainer.get(i)+"\t");
			}
			writer.write("\n");
			writer.flush();


			HashMap<Integer,Stack<ShootingPowerUp>> powerupContainer = controller.getPowerupContainer();
			for(int i = 0; i<powerupContainer.size(); i++) {
				writer.write(powerupContainer.get(i).size()+ "\t");
			}
			writer.write("\n");
			writer.write("\n");
			writer.flush();

			//Shooter Information

			int shooterVelocity = controller.getShooterVelocity(); 
			int shooterOriginXPos = controller.getShooterXPos(); 
			int shooterOriginYPos = controller.getShooterYPos(); 
			double shooterAngle = controller.getShooterAngle(); 
			double[][] shooterCoordinations = controller.getShooterCoordinations(); 
			int atomToShoot = controller.getAtomToShoot(); 
			int shooterHeight = controller.getShooterHeight(); 
			int shooterWidth = controller.getShooterWidth(); 
			int powerupToShoot = controller.getPowerupToShoot();

			writer.write(shooterVelocity+" "+ shooterOriginXPos+ " "+shooterOriginYPos+ " " +shooterAngle+ " " +atomToShoot+ " " +powerupToShoot+ " " +shooterHeight+ " " +shooterWidth);
			writer.write("\n");
			writer.flush();

			for(int i=0; i<shooterCoordinations.length;i++) {
				writer.write(shooterCoordinations[i][0] + " "+shooterCoordinations[i][1]+" ");
			}
			writer.write("\n");
			writer.write("\n");
			writer.flush();

			//GameEnvironment Information
			int gameWidth = controller.getGameWidth();
			int gameHeight = controller.getGameHeight();
			int L = controller.getL();
			int difficultyLevel = controller.getDifficultyLevel();
			writer.write(gameWidth+" "+ gameHeight+ " "+L+ " " +difficultyLevel);
			writer.write("\n");
			writer.flush();

			HashMap<Integer,Integer> objectNumList = controller.getObjectNumList();
			for (int i=0; i<objectNumList.size(); i++) {
				writer.write(objectNumList.get(i)+ "\t");
			}
			writer.write("\n");
			writer.flush();

			ArrayList<List <Moveable>> AtomList = controller.getAtomList();
			for(int i = 0; i<AtomList.size(); i++) {
				List<Moveable> typeAtom = AtomList.get(i);
				for(int k=0; k<typeAtom.size(); k++) {
					AtomAbstract atom = (AtomAbstract) typeAtom.get(k);
					writer.write(atom.getxLoc()+" "+atom.getyLoc()+" "+atom.getHeight()+" "+atom.getWidth()+" "+atom.getDx()+" "+atom.getDy()+" "+atom.getNeutron()+" "+ atom.getProton()+" "+atom.getStability()+" "+ atom.getEfficiency()+"\t");
				}
				writer.write("\n");
				writer.flush();
			}

			ArrayList<List <Moveable>> MoleculeList = controller.getMoleculeList();
			for(int i = 0; i<MoleculeList.size(); i++) {
				List<Moveable> typeMolecule = MoleculeList.get(i);
				for(int k=0; k<typeMolecule.size(); k++) {
					Moveable molecule = typeMolecule.get(k);
					writer.write(molecule.getxLoc()+" "+molecule.getyLoc()+" "+molecule.getHeight()+" "+molecule.getWidth()+" "+molecule.getDx()+" "+molecule.getDy()+"\t");
				}
				writer.write("\n");
				writer.flush();
			}

			List<Integer> moleculeStructure = controller.getMoleculeStructure();
			for (int i=0; i<moleculeStructure.size(); i++) {
				writer.write(moleculeStructure.get(i)+"\t");
			}
			writer.write("\n");
			writer.flush();


			List<Integer> moleculeMov = controller.getMoleculeMov();
			for (int i=0; i<moleculeMov.size(); i++) {
				writer.write(moleculeMov.get(i)+"\t");
			}
			writer.write("\n");
			writer.flush();

			ArrayList<DroppingPowerUp> DroppingPowerUpList = controller.getDroppingPowerUpList();
			for (int i=0; i<DroppingPowerUpList.size(); i++) {
				DroppingPowerUp powerup = DroppingPowerUpList.get(i);
				writer.write(powerup.getxLoc()+" "+powerup.getyLoc()+" "+powerup.getHeight()+" "+powerup.getWidth()+" "+powerup.getDx()+" "+powerup.getDy()+" "+powerup.getType()+ "\t");
			}
			writer.write("\n");
			writer.flush();

			ArrayList<ShootingPowerUp> ShootingPowerUpList = controller.getShootingPowerUpList();
			for (int i=0; i<ShootingPowerUpList.size(); i++) {
				ShootingPowerUp powerup = ShootingPowerUpList.get(i);
				writer.write(powerup.getxLoc()+" "+powerup.getyLoc()+" "+powerup.getHeight()+" "+powerup.getWidth()+" "+powerup.getDx()+" "+powerup.getDy()+" "+powerup.getType()+ "\t");
			}
			writer.write("\n");
			writer.flush();

			ArrayList<ReactionBlocker> ReactionBlockerList = controller.getReactionBlockerList();
			for (int i=0; i<ReactionBlockerList.size(); i++) {
				ReactionBlocker rb = ReactionBlockerList.get(i);
				writer.write(rb.getxLoc()+" "+rb.getyLoc()+" "+rb.getHeight()+" "+rb.getWidth()+" "+rb.getDx()+" "+rb.getDy()+" "+rb.getType()+ "\t");

			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}



	@Override
	public boolean load(String username) {
		File saveFileDir=new File("save_files");
		File saveFile=new File(saveFileDir,username+".txt");
		BufferedReader br;
		try {
			br =new BufferedReader(new FileReader(saveFile));
			if(br != null) {

				//Player Info
				StringTokenizer tk = new StringTokenizer(br.readLine()," ");
				controller.setPlayerHealth(Double.parseDouble(tk.nextToken()));
				controller.setPlayerTime(Double.parseDouble(tk.nextToken()));
				controller.setPlayerScore(Double.parseDouble(tk.nextToken()));
				

				br.readLine();

				//Inventory Info
				tk = new StringTokenizer(br.readLine(),"\t");
				int alphaAtom = Integer.parseInt(tk.nextToken());
				int betaAtom = Integer.parseInt(tk.nextToken());
				int gammaAtom = Integer.parseInt(tk.nextToken());
				int sigmaAtom = Integer.parseInt(tk.nextToken());
				List<Integer> atomNumberList = new ArrayList<>(Arrays.asList(alphaAtom, betaAtom, gammaAtom, sigmaAtom));

				tk = new StringTokenizer(br.readLine(),"\t");
				int eta =  Integer.parseInt(tk.nextToken());
				int lota = Integer.parseInt(tk.nextToken());
				int theta = Integer.parseInt(tk.nextToken());
				int zeta = Integer.parseInt(tk.nextToken());
				List<Integer> shieldNumberList = new ArrayList<>(Arrays.asList(eta, lota, theta, zeta));
				controller.initializeInventoryWhenLoading(atomNumberList, shieldNumberList);

				tk = new StringTokenizer(br.readLine(),"\t");
				int alphaPW = Integer.parseInt(tk.nextToken());
				int betaPW = Integer.parseInt(tk.nextToken());
				int gammaPW = Integer.parseInt(tk.nextToken());
				int sigmaPW = Integer.parseInt(tk.nextToken());
				List<Integer> powerupNumberList = new ArrayList<>(Arrays.asList(alphaPW, betaPW, gammaPW, sigmaPW));
				controller.initializeShootingPowerupWhenLoading(powerupNumberList);

				br.readLine();

				//Shooter Info
				tk = new StringTokenizer(br.readLine()," ");
				int vel = Integer.parseInt(tk.nextToken());
				int orX = Integer.parseInt(tk.nextToken());
				int orY = Integer.parseInt(tk.nextToken());
				double angle = Double.parseDouble(tk.nextToken());
				int atomToShoot = Integer.parseInt(tk.nextToken());
				int pwToShoot = Integer.parseInt(tk.nextToken());
				int shootherH = Integer.parseInt(tk.nextToken());
				int shooterW = Integer.parseInt(tk.nextToken());

				tk = new StringTokenizer(br.readLine(), " ");
				Double x0 = Double.parseDouble(tk.nextToken());
				Double y0 = Double.parseDouble(tk.nextToken());
				Double x1 = Double.parseDouble(tk.nextToken());
				Double y1 = Double.parseDouble(tk.nextToken());
				Double x2 = Double.parseDouble(tk.nextToken());
				Double y2 = Double.parseDouble(tk.nextToken());
				Double x3 = Double.parseDouble(tk.nextToken());
				Double y3 = Double.parseDouble(tk.nextToken());

				List<Double> coord = new ArrayList<>(Arrays.asList(x0, y0, x1, y1, x2, y2, x3, y3));
				controller.initShooterWhenLoading(vel, orX, orY, angle, coord, atomToShoot, pwToShoot);

				//Game Environment
				br.readLine();
				tk = new StringTokenizer(br.readLine());
				int gameWidth = Integer.parseInt(tk.nextToken());
				int gameHeight = Integer.parseInt(tk.nextToken());
				int L = Integer.parseInt(tk.nextToken());
				int difLevel = Integer.parseInt(tk.nextToken());
				controller.setEnvironmentFeatures(L,gameWidth,gameHeight,difLevel);

				tk = new StringTokenizer(br.readLine());
				int Amol = Integer.parseInt(tk.nextToken());
				int Bmol = Integer.parseInt(tk.nextToken());
				int Gmol = Integer.parseInt(tk.nextToken());
				int Smol = Integer.parseInt(tk.nextToken());

				int Arb = Integer.parseInt(tk.nextToken());
				int Brb = Integer.parseInt(tk.nextToken());
				int Grb = Integer.parseInt(tk.nextToken());
				int Srb = Integer.parseInt(tk.nextToken());

				int Apu = Integer.parseInt(tk.nextToken());
				int Bpu = Integer.parseInt(tk.nextToken());
				int Gpu = Integer.parseInt(tk.nextToken());
				int Spu = Integer.parseInt(tk.nextToken());

				
				List<ArrayList<Double>> alphaAtoms = new ArrayList<ArrayList<Double>>();
				String alphaAtomLine=br.readLine();
				if(!alphaAtomLine.equals("\n")) {
					tk = new StringTokenizer(alphaAtomLine,"\t");
					while(tk.hasMoreTokens()) {
						ArrayList<Double> InfoList = new ArrayList<Double>();
						StringTokenizer Atomtk = new StringTokenizer(tk.nextToken()," ");
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						alphaAtoms.add(InfoList);
					}
				}
				List<ArrayList<Double>> betaAtoms = new ArrayList<ArrayList<Double>>();
				String betaAtomLine=br.readLine();
				if(!betaAtomLine.equals("\n")) {
					tk = new StringTokenizer(betaAtomLine,"\t");
					while(tk.hasMoreTokens()) {
						ArrayList<Double> InfoList = new ArrayList<Double>();
						StringTokenizer Atomtk = new StringTokenizer(tk.nextToken()," ");
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						betaAtoms.add(InfoList);
					}
				}
				List<ArrayList<Double>> gammaAtoms = new ArrayList<ArrayList<Double>>();
				String gammaAtomLine=br.readLine();
				if(!gammaAtomLine.equals("\n")) {
					tk = new StringTokenizer(gammaAtomLine,"\t");
					
					while(tk.hasMoreTokens()) {
						ArrayList<Double> InfoList = new ArrayList<Double>();
						StringTokenizer Atomtk = new StringTokenizer(tk.nextToken()," ");
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						gammaAtoms.add(InfoList);
					}
				}
				List<ArrayList<Double>> sigmaAtoms = new ArrayList<ArrayList<Double>>();
				String sigmaAtomLine=br.readLine();
				if(!sigmaAtomLine.equals("\n")) {
					tk = new StringTokenizer(sigmaAtomLine,"\t");
					while(tk.hasMoreTokens()) {
						ArrayList<Double> InfoList = new ArrayList<Double>();
						StringTokenizer Atomtk = new StringTokenizer(tk.nextToken()," ");
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						InfoList.add(Double.valueOf(Atomtk.nextToken()));
						sigmaAtoms.add(InfoList);
					}
				}
				controller.initializingAtomsInEnvironmentWhenLoading(alphaAtoms, betaAtoms, gammaAtoms, sigmaAtoms);
				//////////////////////////////////////////////////////////////////////////////////////////////////////////
				List<ArrayList<Double>> alphaMols = new ArrayList<ArrayList<Double>>();
				String alphaMolLine=br.readLine();
				if(!alphaMolLine.equals("\n")) {
					tk = new StringTokenizer(alphaMolLine,"\t");
					while(tk.hasMoreTokens()) {
						ArrayList<Double> InfoList = new ArrayList<Double>();
						StringTokenizer Moltk = new StringTokenizer(tk.nextToken()," ");
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						alphaMols.add(InfoList);
					}
				}
				List<ArrayList<Double>> betaMols = new ArrayList<ArrayList<Double>>();
				String betaMolLine=br.readLine();
				if(!betaMolLine.equals("\n")) {
					tk = new StringTokenizer(betaMolLine,"\t");
					while(tk.hasMoreTokens()) {
						ArrayList<Double> InfoList = new ArrayList<Double>();
						StringTokenizer Moltk = new StringTokenizer(tk.nextToken()," ");
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						betaMols.add(InfoList);
					}
				}
				List<ArrayList<Double>> gammaMols = new ArrayList<ArrayList<Double>>();
				String gammaMolLine=br.readLine();
				if(!gammaMolLine.equals("\n")) {
					tk = new StringTokenizer(gammaMolLine,"\t");
					
					while(tk.hasMoreTokens()) {
						ArrayList<Double> InfoList = new ArrayList<Double>();
						StringTokenizer Moltk = new StringTokenizer(tk.nextToken()," ");
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						gammaMols.add(InfoList);
					}
				}
				List<ArrayList<Double>> sigmaMols = new ArrayList<ArrayList<Double>>();
				String sigmaMolLine=br.readLine();
				if(!sigmaMolLine.equals("\n")) {
					tk = new StringTokenizer(sigmaMolLine,"\t");
					while(tk.hasMoreTokens()) {
						ArrayList<Double> InfoList = new ArrayList<Double>();
						StringTokenizer Moltk = new StringTokenizer(tk.nextToken()," ");
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						InfoList.add(Double.valueOf(Moltk.nextToken()));
						sigmaMols.add(InfoList);
					}
				}
				controller.initializingMoleculesInEnvironmentWhenLoading(alphaMols, betaMols, gammaMols, sigmaMols);
				
				tk = new StringTokenizer(br.readLine(),"\t");
				List<Integer> moleculeStructure = new ArrayList<>(Arrays.asList(Integer.parseInt(tk.nextToken()),Integer.parseInt(tk.nextToken())));
				controller.setEnvironmentMoleculeStructure(moleculeStructure);
				tk = new StringTokenizer(br.readLine(),"\t");
				Integer mov1=0,mov2=0;
				String st1=tk.nextToken(),st2=tk.nextToken();
				if(!st1.equalsIgnoreCase("null")) {
					mov1=Integer.parseInt(st1);
				}
				if(!st2.equalsIgnoreCase("null")) {
					mov2=Integer.parseInt(st2);
				}
				List<Integer> moleculeMov = new ArrayList<>(Arrays.asList(mov1,mov2));
				controller.setEnvironmentMoleculeMov(moleculeMov);
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				List<ArrayList<Double>> dpList = new ArrayList<ArrayList<Double>>();
				String dpLine=br.readLine();
				if(!dpLine.equals("\n")) {
					tk = new StringTokenizer(dpLine,"\t");
					while(tk.hasMoreTokens()) {
						ArrayList<Double> InfoList = new ArrayList<Double>();
						StringTokenizer Dptk = new StringTokenizer(tk.nextToken()," ");
						InfoList.add(Double.valueOf(Dptk.nextToken()));
						InfoList.add(Double.valueOf(Dptk.nextToken()));
						InfoList.add(Double.valueOf(Dptk.nextToken()));
						InfoList.add(Double.valueOf(Dptk.nextToken()));
						InfoList.add(Double.valueOf(Dptk.nextToken()));
						InfoList.add(Double.valueOf(Dptk.nextToken()));
						InfoList.add(Double.valueOf(Dptk.nextToken()));
						dpList.add(InfoList);
					}
				}
				List<ArrayList<Double>> spList = new ArrayList<ArrayList<Double>>();
				String spLine=br.readLine();
				if(!spLine.equals("\n")) {
					tk = new StringTokenizer(spLine,"\t");
					while(tk.hasMoreTokens()) {
						ArrayList<Double> InfoList = new ArrayList<Double>();
						StringTokenizer Sptk = new StringTokenizer(tk.nextToken()," ");
						InfoList.add(Double.valueOf(Sptk.nextToken()));
						InfoList.add(Double.valueOf(Sptk.nextToken()));
						InfoList.add(Double.valueOf(Sptk.nextToken()));
						InfoList.add(Double.valueOf(Sptk.nextToken()));
						InfoList.add(Double.valueOf(Sptk.nextToken()));
						InfoList.add(Double.valueOf(Sptk.nextToken()));
						InfoList.add(Double.valueOf(Sptk.nextToken()));
						spList.add(InfoList);
					}
				}
				
				List<ArrayList<Double>> rbList = new ArrayList<ArrayList<Double>>();
				String rbLine=br.readLine();
				if(rbLine !=null) {
					tk = new StringTokenizer(rbLine,"\t");
					while(tk.hasMoreTokens()) {
						ArrayList<Double> InfoList = new ArrayList<Double>();
						StringTokenizer rbtk = new StringTokenizer(tk.nextToken()," ");
						InfoList.add(Double.valueOf(rbtk.nextToken()));
						InfoList.add(Double.valueOf(rbtk.nextToken()));
						InfoList.add(Double.valueOf(rbtk.nextToken()));
						InfoList.add(Double.valueOf(rbtk.nextToken()));
						InfoList.add(Double.valueOf(rbtk.nextToken()));
						InfoList.add(Double.valueOf(rbtk.nextToken()));
						InfoList.add(Double.valueOf(rbtk.nextToken()));
						rbList.add(InfoList);
					}
				}
				controller.initializingDroppingPowerupInEnvironmentWhenLoading(dpList, rbList, spList);
				br.close();
				saveFile.delete();
				return true;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

}

