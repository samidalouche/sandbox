package com.dalouche.experiments.rockpaperscissors.symbols;

import static com.dalouche.experiments.rockpaperscissors.symbols.Paper.paper;
import static com.dalouche.experiments.rockpaperscissors.symbols.Rock.rock;
import static com.dalouche.experiments.rockpaperscissors.symbols.Scissors.scissors;
import static com.dalouche.experiments.rockpaperscissors.symbols.Symbols.symbols;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.dalouche.experiments.rockpaperscissors.symbols.Symbol;

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
	
	@Test
	public void shouldFindAllSymbols() {
		assertThat(symbolRepository.findAllSymbols(), is(symbols()));
	}
}
