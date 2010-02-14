package com.dalouche.experiments.rockpaperscissors;

import static com.dalouche.experiments.rockpaperscissors.Paper.paper;
import static com.dalouche.experiments.rockpaperscissors.Rock.rock;
import static com.dalouche.experiments.rockpaperscissors.Scissors.scissors;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DefaultSymbolRepositoryTest {

	private SymbolRepository symbolRepository = new DefaultSymbolRepository();
	
	@Test
	public void shouldFindRock(){
		assertThat(symbolRepository.findSymbolByName("rock"), is((Symbol)rock()));
	}
	
	@Test
	public void shouldFindPaper(){
		assertThat(symbolRepository.findSymbolByName("paper"), is((Symbol)paper()));
	}
	
	@Test
	public void shouldFindScissors(){
		assertThat(symbolRepository.findSymbolByName("scissors"), is((Symbol)scissors()));
	}
	
	@Test
	public void shouldNotFindAnythingElse() {
		try {
			symbolRepository.findSymbolByName("anything");
		} catch(SymbolNotFoundException e) {
			assertThat(e.getName(), is("anything"));
		}
	}
}
