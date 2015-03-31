package com.rukol.mathchallenges;

import java.util.Random;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rukol.improveyourself.BaseChallenge;
import com.rukol.improveyourself.R;

public class SimpleCalculation extends BaseChallenge implements OnClickListener{
	
	final private int kColumn1Id = 11221;
	final private int kColumn2Id = 11222;
	
	private int correctAnswersInRow = 0;
	private int correctAnswer = 0;
	private boolean answersAreSet = false;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	   
		currentActivity = kSimpleCalculationName;
		
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.simpleequation);
	    timer = (TextView)findViewById(R.id.timeRemaining);
	    timer.setText("0:00");
	    addBackButton();
	    addTwoColumns();
	    nextQuestion();
	    showAd();
	    
		mHandler.postDelayed(mUpdateUI, 1000);
	    
	}
	
	protected void timeOver() {
		wrongAnswer();
	}
	
	private void addTwoColumns() {
		
		LinearLayout parent = (LinearLayout)findViewById(R.id.answersContainer);
		parent.setOrientation(LinearLayout.HORIZONTAL);
		parent.setWeightSum(100.0f);
		
		LinearLayout column1 = new LinearLayout(this);
		column1.setOrientation(LinearLayout.VERTICAL);
		
		LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,50.0f);
		column1.setLayoutParams(llParams);
		column1.setId(kColumn1Id);
		column1.setWeightSum(100.0f);
		
		LinearLayout column2 = new LinearLayout(this);
		column2.setOrientation(LinearLayout.VERTICAL);
		
		LinearLayout.LayoutParams llParams2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,50.0f);
		column2.setLayoutParams(llParams2);
		column2.setId(kColumn2Id);
		column2.setWeightSum(100.0f);
		
		parent.addView(column1);
		parent.addView(column2);
		
	}
	
	private void nextQuestion() {
		
		setSecondsRemaining();
		checkLevelIncrease();
		updateStats();
		setEquation();
		
	}
	
	private void setSecondsRemaining() {
		
		if(level <= 50){
			secondsRemaining = 15;
		} else if(level <= 60) {
			secondsRemaining = 14;
		} else if(level <= 80) {
			secondsRemaining = 12;
		} else {
			secondsRemaining = 10;
		}
		displayTime();
		
	}
	
	private void setEquation() {
		
		String equation = generateEquation();
		System.out.println("equation = "+equation);
		TextView equationText = (TextView)findViewById(R.id.equationText);
		if(equationText != null) {
			equationText.setText(equation);
		}
		
	}
	
	private String generateEquation() {
		
		int firstNumber = getNumber();
		int result = firstNumber;
		StringBuilder equation = new StringBuilder();
		equation.append(String.valueOf(firstNumber));
		
		int numberOfactions = numberOfActions();
		
		Random randomGenerator = new Random();
		for(int i = 0; i < numberOfactions; i++) {
			
			int nextNumber = getNumber();
			int action = getAction(randomGenerator);
			
			if(action == 1) {
				
				equation.append(" - ");
				result -= nextNumber;
				
			} else {
				
				equation.append(" + ");
				result += nextNumber;
				
			}
			
			equation.append(String.valueOf(nextNumber));
			
		}
		System.out.println("result = "+result);
		correctAnswer = result;
		displayAnswers();
		return equation.toString();
		
	}
	
	private int getNumber() {
		
		Random randomGenerator = new Random();
		int randomMax = (level*3) + 15;
		int randomInt = randomGenerator.nextInt(randomMax);
		
		return randomInt;
		
	}
	
	private int getAction(Random randomGenerator) {
		
		int randomNumber = randomGenerator.nextInt(3);
		return randomNumber;
		
	}
	
	private int numberOfActions() {
		
		return 1 + (int)((10+level)/25);
		
	}
	
	private int[] generateAnswerOptions() {
		
		int numberOfAnswers = 8;
		Random randomGenerator = new Random();
		
		int correctAnswerPosition = randomGenerator.nextInt(numberOfAnswers);
		int[] answers = new int[numberOfAnswers];
		for(int i = 0; i < numberOfAnswers; i++) {
			
			if(i == correctAnswerPosition) {
				answers[i] = correctAnswer;
			} else {
				
				boolean unique = false;
				int j = 0;
				int answerValue = 0;
				while(!unique) {
					j ++;
					if(j > 4 && j < 12) {
						answerValue = correctAnswer + (j * 2);
						unique = checkElementUnique(answers, answerValue);
						System.out.println("first" + answerValue);
					} else if(j > 8) {
						unique = true;
						System.out.println("second");
					} else {
						answerValue = generateAnswerOptionValue(randomGenerator);
						System.out.println("third"+answerValue);
						unique = checkElementUnique(answers, answerValue);
					}
					
				}
				answers[i] = answerValue;
				
			}
			
		}
		
		return answers;
		
	}
	
	private int generateAnswerOptionValue(Random randomGenerator) {
		
		int delta = 0;
		if(Math.abs(correctAnswer) > 10) {
			
			if(randomGenerator.nextInt(3) == 1) {
				//last digit similar
				delta = (int)correctAnswer/300;
				delta ++;
				delta *= 10;
				if(randomGenerator.nextInt(2) == 1) {
					delta *= -1;
				}
				
			} else {
				int offset = (int)(Math.abs(correctAnswer) * 0.30);
				offset += 7;
				delta = randomGenerator.nextInt(offset);
				delta -= (int)(delta/2);
			}
			 
		} else {
			
			delta = randomGenerator.nextInt(10);
			delta -= (int)(delta/2);
			
		}
		
		return (correctAnswer + delta);
		
	}
	
	private boolean checkElementUnique(int[] arr, int element) {
		
		if(correctAnswer == element) {
			return false;
		}
		
		int length = arr.length;
		for(int i = 0; i < length; i++) {
			if(arr[i] == element) {
				return false;
			}
		}
		return true;
		
	}
	
	private void displayAnswers() {
		
		int[] answers = generateAnswerOptions();
		for (int i = 0; i < answers.length; i++) {
			int answer = answers[i];
			if(answersAreSet) {
				Button answerOption = (Button)findViewById(10+i);
				answerOption.setText(String.valueOf(answer));
				answerOption.setTag(answer);
			} else {
				LinearLayout answersContainer = null;
				if(i%2 == 0) {
					answersContainer = (LinearLayout)findViewById(kColumn1Id);
				} else {
					answersContainer = (LinearLayout)findViewById(kColumn2Id);
				}
				addButton(answersContainer, String.valueOf(answer), 10+i, answer, 18.0f, true);
			}
		}
		answersAreSet = true;
		
	}
	
	private void correctAnswer() {
		
		correctAnswersInRow ++;
		correct();
		nextQuestion();
		
	}
	
	private void wrongAnswer() {
		
		correctAnswersInRow = 0;
		wrong();
		nextQuestion();
		
	}
	
	private void updateStats() {
		
		TextView correctAnswers = (TextView)findViewById(R.id.correctAnswers);
		if(correctAnswers != null) {
			correctAnswers.setText(String.valueOf(correctAnswersInRow));
		}
		
		TextView correctAnswersRequired = (TextView)findViewById(R.id.correctAnswersRequired);
		if(correctAnswersRequired != null) {
			correctAnswersRequired.setText(String.valueOf(" / "+requiredCorrectAnswers()));
		}
		
		updateLevelTv();
		
	}
	
	private void checkLevelIncrease() {
		
		if(correctAnswersInRow >= requiredCorrectAnswers()) {
			correctAnswersInRow = 0;
			increaseLevel();
		}
		
	}
	
	private int requiredCorrectAnswers() {
		
		return 3 + (int)(level/5);
		
	}

	@Override
	public void onClick(View v) {
		
		Button clickedButton = (Button)v;
		
		if(clickedButton == null) {
			return;
		}
		
		if(clickedButton.getId() == kBackButtonId) {
			
			finish();
			
		} else {
		
			int value = (Integer) clickedButton.getTag();
			if(value == correctAnswer) {
				correctAnswer();
			} else {
				wrongAnswer();
			}
			
		}
	}

	
	
	@Override
	public void onResume() {
		nextQuestion();
		super.onResume();
	}


	@Override
	public void onDestroy() {
		mHandler.removeCallbacks(mUpdateUI);
		super.onDestroy();
	}
	
	
	
}
