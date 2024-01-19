import java.util.Scanner;

public class BingoTest2 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int size = 5;	// scanner를 이용해서 배열의 길이를 지정해도 된다.
		
		int[][] bingo = new int[size][size];	// size 크기의 2차원 배열 생성
		int x = 0, y = 0;	// bingo 배열을 섞을 때 사용하는 배열의 인덱스
		int num = 0;	// 빙고판에서 지울 숫자 => 입력 받을 숫자.
		int n = 0;
		
		for(int i=0; i<bingo.length; i++) {
			for(int j=0; j<bingo[i].length; j++) {
				bingo[i][j] = i * size + j + 1; 
			}
		}
		
		for(int i=0; i<bingo.length; i++) {
			for(int j=0; j<bingo[i].length; j++) {
				System.out.printf("%3d ", bingo[i][j]);
			}
			System.out.println();
		}
		System.out.println("=============================");
		
		for(int i=0; i<bingo.length; i++) {
			for(int j=0; j<bingo[i].length; j++) {
				x = (int)(Math.random() * size); 
				y = (int)(Math.random() * size);
				
				int tmp = bingo[i][j];
				bingo[i][j] = bingo[x][y];
				bingo[x][y] = tmp;
			}
		}
		
		
		
		do {
			for(int i=0; i<bingo.length; i++) {
				for(int j=0; j<bingo[i].length; j++) {
					System.out.printf("%3d ", bingo[i][j]);
				}
				System.out.println();
			}
			System.out.println("=============================");
			
			System.out.printf("1 ~ %d 사이의 숫자를 입력하세요.(종료 0)> ",
					size * size);
			String tmp = scanner.nextLine();
			num = Integer.parseInt(tmp);
			
//			break; 문은 가까운 반복문만 빠져나간다 => 중첩되어 있는 반복문을 한번에 빠져 나갈
//			수 없다.
//			반복문 앞에 반복문을 식별할 수 있는 이름을 지정(이름:)을 하면 break 반복문이름; 으로 
//			중첩되어 있는 반복문을 빠져 나갈 수 있다.
			outer:
			for(int i=0; i<bingo.length; i++) {
				for(int j=0; j<bingo[i].length; j++) {
					if(bingo[i][j] == num) {
						bingo[i][j] = 0;
						break outer;
					}
				}
				System.out.println("외부 반복입니다.");
			}
			
				
		} while(num != 0);
		

		
		
	}

}








