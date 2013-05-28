package br.com.jpa.panel;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.metamodel.EntityType;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.springframework.context.annotation.Scope;

import br.com.jpa.table.ColumnBuilder;
import br.com.jpa.table.JpaClientTable;
import br.com.jpa.table.ReflectionTableModel;
import br.com.jpa.table.column.Column;

@Named
@Scope("prototype")
public class JpaQueryPanel extends JPanel {

	private static final long serialVersionUID = 2047123842587630718L;

	private RTextScrollPane scrollPane;
	private RSyntaxTextArea textArea;
	private JpaClientTable table;

	private CompletionProvider completionProvider;
	
	@Inject
	private ColumnBuilder columnBuilder;

	@Inject
	private EntityManagerFactory factory;
	
	private EntityManager entityManager;

	public JpaQueryPanel() {
		super();
	}

	public void init() {

		entityManager = factory.createEntityManager();
		
		textArea = new RSyntaxTextArea();
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
		textArea.setAnimateBracketMatching(true);

		scrollPane = new RTextScrollPane(textArea);
		scrollPane.setLineNumbersEnabled(true);

		createAutoCompletion();
		
		textArea.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent evt) {
			}

			@Override
			public void keyReleased(KeyEvent evt) {
			}

			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER
						&& evt.isControlDown()) {
					query();
				}
			}
		});

		table = new JpaClientTable();

		JScrollPane pane = new JScrollPane();
		pane.setViewportView(table);

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				scrollPane, pane);
		splitPane.setDividerLocation(250);
		
		this.setLayout(new BorderLayout());
		this.add(splitPane, BorderLayout.CENTER);
	}

	private void query() {
		String jpql = getQuery();
		Query query = getEntityManager().createQuery(jpql);

		setParameters(query);

		try {
			List<Object> rows = query.getResultList();
			List<Column> columns = columnBuilder.buildColumns(rows);
			ReflectionTableModel model = new ReflectionTableModel(rows, columns);
			table.setModel(model);
		} catch (Exception e) {
			handleError(e);
		}
	}

	private void createAutoCompletion() {
		DefaultCompletionProvider provider = new DefaultCompletionProvider();
		Iterator<EntityType<?>> iterator = getEntityManager().getMetamodel()
				.getEntities().iterator();

		while (iterator.hasNext()) {
			EntityType<?> next = iterator.next();
			String entity = next.getClass().getSimpleName();
			provider.addCompletion(new BasicCompletion(provider, entity));
		}
		
		AutoCompletion ac = new AutoCompletion(provider);
		ac.install(textArea);


		completionProvider = provider;
	}
	
	private void handleError(Exception error) {
		error.printStackTrace();
	}

	private void setParameters(Query query) {
		// TODO
	}

	private String getQuery() {
		return textArea.getSelectedText() == null ? textArea.getText()
				: textArea.getSelectedText();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
