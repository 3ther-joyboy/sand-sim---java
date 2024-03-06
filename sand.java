import java.util.Scanner;

public class Main
{ 
	public static int count = 0;
		public static void render(particle [][] world, int size){
		System.out.print("\033c");
		for(int i = 0;i<size;i++){
             for(int y = 0;y<size;y++){
				 try{System.out.print(" " + "\033[" + world[i][y].color + "m" + world[i][y].charakter);}
				 catch(Exception e){
					 System.out.print("\033[0;31m #");
				 }
			 }
			 System.out.println();
         }
		System.out.println("\033[1;37m " + count);
	}
	
	public static particle [][] update(particle [][] world, int size){
		particle worldUp[][] = new particle[size][size];
		count++;
//		for(int i = 0; i < size; i++)
//			for(int y = 0; y < size; y++)
//				worldUp[i][y] = new particle(world[i][y]);

		for(int i = 0;i<size;i++){
			for(int y = 0;y<size;y++){
			if(world[i][y].updated)
				switch (world[i][y].type) {
					case 0:
						if(worldUp[i][y]==null)worldUp[i][y]=world[i][y];
						break;
					case 1:
						   if(
									i+1 < size &&
									world[i+1][y].mass < world[i][y].mass && 
									world[i+1][y].type != 1 &&
									world[i+1][y].updated
							){
										worldUp[i+1][y] = new particle(world[i][y]);
										worldUp[i][y] = new particle(world[i+1][y]);
										world[i][y].updated = world[i+1][y].updated  = false;

						   }else if(
									i+1 < size &&
									y+1 < size &&
									world[i+1][y+1].mass < world[i][y].mass &&
									world[i+1][y+1].type != 1 &&
									world[i+1][y+1].updated
									){
										worldUp[i+1][y+1] = new particle(world[i][y]);
										worldUp[i][y] = new particle(world[i+1][y+1]);
									world[i+1][y+1].updated = world[i][y].updated  = false;
						   }else if(
									i+1 < size &&
				                    y-1 >= 0 &&
						            world[i+1][y-1].mass < world[i][y].mass &&
								    world[i+1][y-1].type != 1 &&
									world[i+1][y-1].updated
							){
										worldUp[i+1][y-1] = new particle(world[i][y]);
										worldUp[i][y] = new particle(world[i+1][y-1]);
									world[i+1][y-1].updated = world[i][y].updated  = false;
						   }else{if(worldUp[i][y]==null)worldUp[i][y]=world[i][y];} 
						   break;
					case 2:
						   if(
									i+1 < size &&
									world[i+1][y].mass < world[i][y].mass && 
									world[i+1][y].type != 1 &&
									world[i+1][y].updated
									){
										worldUp[i+1][y] = new particle(world[i][y]);
										worldUp[i][y] = new particle(world[i+1][y]);
									world[i+1][y].updated = world[i][y].updated  = false;
						   }else if(
									i+1 < size &&
									y+1 < size &&
									world[i+1][y+1].mass < world[i][y].mass &&
									world[i+1][y+1].type != 1 &&
									world[i+1][y+1].updated
							 ){
										worldUp[i+1][y+1] = new particle(world[i][y]);
										worldUp[i][y] = new particle(world[i+1][y+1]);
									world[i+1][y+1].updated = world[i][y].updated  = false;
						   }else if(
									i+1 < size &&
				                    y-1 >= 0 &&
						            world[i+1][y-1].mass < world[i][y].mass &&
								    world[i+1][y-1].type != 1 &&
									world[i+1][y-1].updated
							){
										worldUp[i+1][y-1] = new particle(world[i][y]);
										worldUp[i][y] = new particle(world[i+1][y-1]);
									world[i+1][y-1].updated = world[i][y].updated = false;

						   }else if(
									y+1 < size &&
									y-1 >= 0 &&
									world[i][y-1].mass < world[i][y].mass &&
									world[i][y+1].mass < world[i][y].mass &&
									world[i][y-1].updated &&
									world[i][y+1].updated
							){
								long rand = Math.round(Math.random());
								if(rand==0){
											worldUp[i][y+1] = new particle(world[i][y]);
											worldUp[i][y] = new particle(world[i][y+1]);
										world[i][y+1].updated = world[i][y].updated = false;
								}else{
										worldUp[i][y-1] = new particle(world[i][y]);
										worldUp[i][y] = new particle(world[i][y-1]);
									world[i][y-1].updated = world[i][y].updated = false;
								}
							
						   }else if(
									y+1 < size &&
									world[i][y+1].mass < world[i][y].mass &&
									world[i][y+1].updated
							){
										worldUp[i][y+1] = new particle(world[i][y]);
										worldUp[i][y] = new particle(world[i][y+1]);
									world[i][y+1].updated = world[i][y].updated = false;
						   }else if(
									y-1 >= 0 &&
									world[i][y-1].mass < world[i][y].mass &&
									world[i][y-1].updated
							){
										worldUp[i][y-1] = new particle(world[i][y]);
										worldUp[i][y] = new particle(world[i][y-1]);
									world[i][y-1].updated = world[i][y].updated = false;	
						   }else if(worldUp[i][y]==null){
									worldUp[i][y]=world[i][y];
							}
					}
				}
		}
		return worldUp;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String white = "1;37";
		int size = 50;
		particle world[][] = new particle[size][size];

		for(int i = 0; i < size; i++){
			for(int y = 0; y < size; y++){
				world[i][y] = new particle(0,2,' ',white);
			}
		}

		while(true){
		if(count < 100)	world[0][5] = new particle(1,1,'%',"1;33");
		if(count < 500)		world[0][size-30] = new particle(0.8f,2,'~',"1;34");
		if(count < 300)	world[0][size-5] = new particle(0.5f,2,'~',"1;35");
		if(count < 200 && count%2 == 0)	world[20][10] = new particle(-0.1f,2,'@',white);

			render(world,size);
			world = update(world,size);
			String asdf = scanner.nextLine();
		}
	}
}

class particle{
	float mass;
	int type;
	char charakter;
	String color;
	boolean updated = true;

	particle(float mass, int type, char charakter, String color ){
		this.mass = mass;
		this.type = type;
		this.charakter = charakter;
		this.color = color;

	}
	particle(particle other){
		this.mass = other.mass;
		this.type = other.type;
		this.charakter = other.charakter;
		this.color = other.color;
	}
}
