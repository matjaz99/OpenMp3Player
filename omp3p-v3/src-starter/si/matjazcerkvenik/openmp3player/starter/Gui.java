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
	private JButton btnUpdate = new JButton("Update");

	private JPanel pnlControls = new JPanel();
	
	private Updater u = new Updater();

	public Gui() {

		super("Starter");
		pnlControls.setLayout(new GridLayout(4, 1));
		lblTitle.setText("OpenMp3Player v" + Start.version);
		pnlControls.add(lblTitle);
		pnlControls.add(lblStatus);
		pnlControls.add(btnStartStop);
		pnlControls.add(btnUpdate);
		
		if (Start.isServerRunning()) {
			lblStatus.setText("Status: Running");
			btnStartStop.setText("Stop server");
		}
				
		if (!Start.isServerRunning() && u.isUpdateRequired()) {
			btnUpdate.setEnabled(true);
		} else {
			btnUpdate.setEnabled(false);
		}
		
		
		

		add(pnlControls);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		btnStartStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startStopTomcat();
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				u.updateOmp3p();
				Start.version = Updater.lastVersion;
				lblTitle.setText("OpenMp3Player v" + Start.version);
				btnUpdate.setEnabled(false);
			}
		});

	}

	/**
	 * Implementation of start/stop button.
	 */
	private void startStopTomcat() {
		if (Start.isServerRunning()) {
			Start.stopServer();
			lblStatus.setText("Status: Stopped");
			btnStartStop.setText("Start server");
			if (u.isUpdateRequired()) {
				btnUpdate.setEnabled(true);
			} else {
				btnUpdate.setEnabled(false);
			}
		} else {
			Start.startServer();
			lblStatus.setText("Status: Running");
			btnStartStop.setText("Stop server");
			btnUpdate.setEnabled(false);
		}
	}

}
