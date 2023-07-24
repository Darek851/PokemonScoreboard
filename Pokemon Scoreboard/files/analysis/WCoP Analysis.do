cd "C:/Users/kadia/OneDrive/Documents/Pokemon Scoreboard/files"
import delimited data.csv, clear

drop if turns_active == 0
tempfile mycopy
save `mycopy'
contract pokemon, freq(use_count)
mer 1:m pokemon using `mycopy'
drop _merge
gen tera_freq = use_count * tera
rename pct_dmg_healed pct_healed

save "data.dta", replace

global p r(p100)
global no_kgb if pokemon != "Kingambit"

cap program drop pokemon_regression

program pokemon_regression
	reghdfe turns_active tera if pokemon == "`1'" & inrange(turns_active, 0, $p ), noabsorb
	eststo m1
	reghdfe times_brought_in tera if pokemon == "`1'" & inrange(times_brought_in, 0, $p ), noabsorb
	eststo m2
	reghdfe pct_dmg_dealt tera if pokemon == "`1'" & inrange(turns_active, 0, $p ), noabsorb
	eststo m3
	reghdfe kos tera if pokemon == "`1'" & inrange(kos, 0, $p ), noabsorb
	eststo m4
	reghdfe hits_taken tera if pokemon == "`1'" & inrange(hits_taken, 0, $p ), noabsorb
	eststo m5
	reghdfe pct_healed tera if pokemon == "`1'" & inrange(pct_healed, 0, $p ), noabsorb
	eststo m6
	esttab m1 m2 m3 m4 m5 m6 using TeraRegressions.csv, drop(_cons) b(3) t(3) append title(Effect of Terastalization on "`1'" Performance) nonumbers
end

reghdfe turns_active tera if inrange(turns_active, 0, $p ), absorb(pokemon)
eststo m1
reghdfe times_brought_in tera if inrange(times_brought_in, 0, $p ), absorb(pokemon)
eststo m2
reghdfe pct_dmg_dealt tera if inrange(pct_dmg_dealt, 0, $p ), absorb(pokemon)
eststo m3
reghdfe kos tera if inrange(kos, 0, $p ), absorb(pokemon)
eststo m4
reghdfe hits_taken tera if inrange(hits_taken, 0, $p ), absorb(pokemon)
eststo m5
reghdfe pct_healed tera if inrange(pct_healed, 0, $p ), absorb(pokemon)
eststo m6
esttab m1 m2 m3 m4 m5 m6 using TeraRegressions.csv, drop(_cons) b(3) t(3) replace title(Effect of Terastalization on Pokemon Performance) nonumbers

pokemon_regression Kingambit
pokemon_regression Garganacl
pokemon_regression "Iron Valiant"
pokemon_regression Baxcalibur

reghdfe turns_active tera $no_kgb & inrange(turns_active, 0, $p ), absorb(pokemon)
eststo m1
reghdfe times_brought_in tera $no_kgb & inrange(times_brought_in, 0, $p ), absorb(pokemon)
eststo m2
reghdfe pct_dmg_dealt tera $no_kgb & inrange(pct_dmg_dealt, 0, $p ), absorb(pokemon)
eststo m3
reghdfe kos tera $no_kgb & inrange(kos, 0, $p ), absorb(pokemon)
eststo m4
reghdfe hits_taken tera $no_kgb & inrange(hits_taken, 0, $p ), absorb(pokemon)
eststo m5
reghdfe pct_healed tera $no_kgb & inrange(pct_healed, 0, $p ), absorb(pokemon)
eststo m6
esttab m1 m2 m3 m4 m5 m6 using TeraRegressions.csv, drop(_cons) b(3) t(3) append title(Effect of Terastalization on Pokemon Performance no Kingambit) nonumbers

/*
hist kos, discrete title(Distribution of KOs by All Pokemon)
graph export "AllKOsDist.png", as(png) replace
hist kos if tera == 1, discrete title(Distribution of KOs by Terastallized Pokemon)
graph export "TeraKOsDist.png", as(png) replace


preserve
collapse turns_active times_brought_in kos pct_dmg_dealt pct_dmg_taken pct_healed hits_taken tera lead team_win use_count tera_freq, by(pokemon)
save WCoP_data_by_pokemon.dta, replace


use WCoP_data_by_pokemon, clear
drop if use_count < 5









