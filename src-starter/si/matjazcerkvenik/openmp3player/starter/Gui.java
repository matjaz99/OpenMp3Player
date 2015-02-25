package si.matjazcerkvenik.openmp3player.starter;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Simple swing GUI for starting/stopping Tomcat server.
 * 
 * @author matjaz
 *
 */
public class Gui extends JFrame {

	private static final long serialVersionUID = 2781903683927171982L;

	private JLabel lblTitle = new JLabel("OpenMp3Player");
	private JLabel lblStatus = new JLabel("Status: Stopped");
	private JButton btnStartStop = new JButton("Start server");

	private JPanel pnlControls = new JPanel();

	public Gui() {

		super("Starter");
		pnlControls.setLayout(new GridLayout(3, 1));
		lblTitle.setText("OpenMp3Player v" + Start.readVersion());
		pnlControls.add(lblTitle);
		if (Start.runningFileExist()) {
			lblStatus.setText("Status: Running");
			btnStartStop.setText("Stop server");
		}
		pnlControls.add(lblStatus);
		pnlControls.add(btnStartStop);

		add(pnlControls);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		btnStartStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startStopTomcat();
			}
		});

	}

	/**
	 * Implementation of start/stop button.
	 */
	private void startStopTomcat() {
		if (Start.runningFileExist()) {
			Start.stopServer();
			lblStatus.setText("Status: Stopped");
			btnStartStop.setText("Start server");
		} else {
			Start.startServer();
			lblStatus.setText("Status: Running");
			btnStartStop.setText("Stop server");
		}
	}

}
