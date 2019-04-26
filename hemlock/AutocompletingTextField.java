package hemlock;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AutocompletingTextField extends JTextField implements DocumentListener, KeyListener
{
	private List<String> options;
	private boolean backspace = false;
	private boolean completionInProgress = false;
	
	private int firstMatch = -1;
	private int matchIndex = -1;
	private int lastMatch = -1;

	public AutocompletingTextField()
	{
		super();
		getDocument().addDocumentListener(this);
		addKeyListener(this);
	}
	
	public void setOptions(List<String> options)
	{
		this.options = options;
		Collections.sort(this.options);
		updateCompletion();
	}
	
	private void updateCompletion()
	{
		if (!completionInProgress)
		{
			completionInProgress = true;
			
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					if (options == null)
					{
						completionInProgress = false;
						return;
					}
					
					AutocompletingTextField textField = AutocompletingTextField.this;

					String text = getText();
					
					int textLen = text.length();

					if (getSelectionStart() != getSelectionEnd())
					{
						textLen = getSelectionStart();
					}

					if (backspace)
					{
						backspace = false;
						--textLen;
					}

					if (textLen != text.length())
					{
						text = text.substring(0, textLen);
					}
					
					textField.updateMatches(text);
					
					if (matchIndex >= 0)
					{
						String match = options.get(matchIndex);
						textField.setText(match);
						textField.setCaretPosition(match.length());
						textField.moveCaretPosition(text.length());
					}
					else
					{
						textField.setText(text);
					}
	
					textField.completionInProgress = false;
				}
			});
		}
	}
	
	private void updateMatches(String text)
	{
		firstMatch = -1;
		lastMatch = -1;
		
		if (!text.isEmpty())
		{
			firstMatch = -Collections.binarySearch(options, text) - 1;
			
			if (firstMatch >= 0 && firstMatch < options.size() && options.get(firstMatch).startsWith(text))
			{
				int nextMatch = firstMatch + 1;
				
				while (nextMatch < options.size() && options.get(nextMatch).startsWith(text))
				{
					++nextMatch;
				}
				
				lastMatch = nextMatch - 1;
			}
			else
			{
				firstMatch = -1;
			}
		}

		matchIndex = Math.min(lastMatch, Math.max(firstMatch, matchIndex));
	}
	
	private void nextMatch()
	{
		if (matchIndex >= 0)
		{
			++matchIndex;
			
			if (matchIndex > lastMatch)
			{
				matchIndex = firstMatch;
			}
		}
	}
	
	private void priorMatch()
	{
		if (matchIndex >= 0)
		{
			--matchIndex;
			
			if (matchIndex < firstMatch)
			{
				matchIndex = lastMatch;
			}
		}
	}

	@Override
	public void changedUpdate(DocumentEvent ev)
	{
		updateCompletion();
	}

	@Override
	public void removeUpdate(DocumentEvent ev)
	{
		updateCompletion();
	}

	@Override
	public void insertUpdate(DocumentEvent ev)
	{
		updateCompletion();
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		//TextDemo.print("keyPressed " + e.toString());
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_BACK_SPACE:
			if (getSelectionStart() != getSelectionEnd())
			{
				backspace = true;
			}
			break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_KP_UP:
			if (matchIndex >= 0)
			{
				// Select the prior matching selection.
				priorMatch();
				updateCompletion();
				e.consume();
			}
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_KP_DOWN:
			if (matchIndex >= 0)
			{
				// Select the next matching selection.
				nextMatch();
				updateCompletion();
				e.consume();
			}
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_KP_RIGHT:
			// Confirm the current selection
			if (getSelectionStart() != getSelectionEnd() && getSelectionEnd() == getText().length())
			{
				setCaretPosition(getText().length());
				e.consume();
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}
}