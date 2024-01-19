import java.util.Scanner;

public class BingoTest {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int size = 5;	// scanner를 이용해서 배열의 길이를 지정해도 된다.
		
		int[][] bingo = new int[size][size];	// size 크기의 2차원 배열 생성
		int x = 0, y = 0;	// bingo 배열을 섞을 때 사용하는 배열의 인덱스
		int num = 0;	// 빙고판에서 지울 숫자 => 입력 받을 숫자.
		int n = 0;
		
//		배열의 모든 요소를 1 부터 size * size 까지의 숫자로 초기화
		for(int i=0; i<bingo.length; i++) {
			for(int j=0; j<bingo[i].length; j++) {
//				bingo[i][j] = ++n;
//				i = 0 => 1, 2, 3, 4, 5 / i = 1 => , 6, 7, 8, 9, 10
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
		
		
//		배열의 저장된 값을 섞는다. 
//		=> x, y의 값을 랜덤하게 만든 후 기존의 배열 요소의 값과 x, y를 주소로 하는 배열의
//		요소의 값을 바꾼다. => a[i][j] <=> a[x][y]
		for(int i=0; i<bingo.length; i++) {
			for(int j=0; j<bingo[i].length; j++) {
				x = (int)(Math.random() * size); // Math.random() * 5 => 1 ~ 4.99..
				y = (int)(Math.random() * size);
				
//				bingo[i][j]와 임의로 선택된 값 bingo[x][y]를 바꾼다.
				int tmp = bingo[i][j];
				bingo[i][j] = bingo[x][y];
				bingo[x][y] = tmp;
			}
		}
		for(int i=0; i<bingo.length; i++) {
			for(int j=0; j<bingo[i].length; j++) {
				System.out.printf("%3d ", bingo[i][j]);
			}
			System.out.println();
		}
		System.out.println("=============================");
		
		
//		num값을 입력 받고 num값에 해당하는 숫자를 찾아서(배열의 요소) 0으로 바꿔주고,
//		입력받은 값이 0이면 반복문을 빠져 나간다.
		while(true) {
			System.out.printf("1 ~ %d 사이의 숫자를 입력하세요.(종료 0)> ",
					size * size);
//			num = scanner.nextInt();
			String tmp = scanner.nextLine();
			num = Integer.parseInt(tmp);
			
			if(num == 0) {
				break;
			}
			
			boolean flag = false;
			
			for(int i=0; i<bingo.length; i++) {
				for(int j=0; j<bingo[i].length; j++) {
					if(bingo[i][j] == num) {
						bingo[i][j] = 0;
						flag = true;
						break;
					}
				}
				if(flag) {
					break;
				}
				System.out.println("숫자를 0으로 바꿉니다.");
			}
			for(int i=0; i<bingo.length; i++) {
				for(int j=0; j<bingo[i].length; j++) {
					System.out.printf("%3d ", bingo[i][j]);
				}
				System.out.println();
			}
			System.out.println("=============================");
			
		}
		
		
		
	}

}








