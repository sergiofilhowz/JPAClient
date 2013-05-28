package br.com.jpa.window;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.springframework.context.ApplicationContext;

import br.com.jpa.panel.JpaQueryPanel;

public class JpaClientWindow extends JFrame {

	private static final long serialVersionUID = 6225765073690825989L;
	
	public JpaClientWindow(ApplicationContext context) {
		this.getContentPane().setLayout(new BorderLayout());
		
		JpaQueryPanel panel = context.getBean(JpaQueryPanel.class);
		panel.init();
		
		this.getContentPane().add(panel, BorderLayout.CENTER);
		
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
