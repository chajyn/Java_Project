package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

<<<<<<< HEAD
import javax.swing.JOptionPane;

=======
import Model.ImageData;
>>>>>>> ac2e3653a6e315c9f3f20eb6336c364bfa6423bd
import Model.Match;
import Model.Music;
import Model.TileGrid;
import View.GameBoardPanel;
import View.OrchardView;

public class OrchardController {

	private OrchardView _orchardView;
	private GameBoardPanel _gameBoard;
	private Music backgroundMusic;
	
	private TileGrid grid;
	private Match match;
	private Thread matchThread;
	
	private ImageData imgData;
	
	public OrchardController() {
		
		imgData = new ImageData();
		_orchardView = new OrchardView();
		_orchardView.addStartbtnListener(new startbtnListener());
		_orchardView.addExitbtnListener(new exitbtnListener());
		
		try {
			AuthAPI.connectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		backgroundMusic = new Music("BackgroundMusic.mp3", true);
		backgroundMusic.start();
	}
	
	
	// 게임 시작화면으로 전환, 배경음악 전환 및 타이머 스타트
	public void gameStart() {

			grid = new TileGrid();
			match = new Match(grid);
			matchThread = new Thread(match);
			matchThread.start();
			
			_gameBoard = new GameBoardPanel(grid);
			_gameBoard.addGameListener(new gameListener());
			_orchardView.changeToGameView(_gameBoard);

			backgroundMusic.close();
			backgroundMusic = new Music("ProcessGameMusic.mp3", true);
			backgroundMusic.start();
			
	}
		
	public void endGame() {
		
		grid = null;
		
		// singleton 활용방안 모색
		match.stop();
		match = null;
		
		_gameBoard = null;
		_orchardView.changeToLoginView();
		
		backgroundMusic.close();
		backgroundMusic = new Music("BackgroundMusic.mp3", true);
		backgroundMusic.start();
	}
	
	private class gameListener implements MouseListener {
		@Override
		public void mousePressed(MouseEvent e) {
			grid.addCheckCount();
			grid.clickCheck(e.getPoint());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}
	}
	
	private class startbtnListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			boolean isValid;
			isValid = _orchardView.checkValid();
			if(isValid) {
				JOptionPane.showMessageDialog(_orchardView,"Do you want to Start game?!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
				gameStart();
			} else {
				JOptionPane.showMessageDialog(_orchardView,"Please check your ID or Password!", "Login Failed", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
	
	private class exitbtnListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			System.exit(1);
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}
		
	}
}
