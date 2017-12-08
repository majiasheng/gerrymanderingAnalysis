<%@include file="/WEB-INF/views/include/header.jsp" %>
<h2>Help</h2>
<hr>
<div>
	<div>
		<h3>What is gerrymendaring?</h3>
		<p>
			Gerrymandering is a process of drawing political boundaries to give a party a numeric advantage over an opposing party.
		</p>
	<div>

	<hr>

	<div>
		<h3>How to use this website?</h3>
		<p>
			We tried to have each feature to be self explainatory. Give it a try! 
		</p>
	<div>

	<hr>
        
        <div>
		<h3>Explanation of Tests</h3>
                <hr>
                <h4>Efficiency Gap</h4>
		<p>
			The Efficiency Gap Test is a test that aggregates the number of votes wasted for
                        each party in the districts of a state. A wasted vote is a vote that that was not necessary
                        for the winning party to win the election in a district. Every winning party vote above 50% of the 
                        total vote would count as a wasted vote for the winning party, while all the votes for the losing
                        party would count as a wasted vote for the losing party. The percent difference between the amount
                        of wasted votes for each party is then found by subtracting the 2 running totals from each other
                        before dividing by the total number of votes in the state. If this percentage is above a certain threshold 
                        for a given state, then the state is considered gerrymandered.
		</p>
                <br>
                <h4>Mean-Median Difference</h4>
		<p>
			The Mean-Median Difference is calculated by finding the difference between the average percentage of
                        votes a party gets per district in a state and the median percentage of votes a given party gets in a state.
                        a standard score can then be calculated by dividing by the standard error. This score can then be
                        checked against a normal distribution curve. If the value found above a certain value on the distribution,
                        the state will be considered gerrymandered.
		</p>
                <br>
                
                <h4>T Test</h4>
		<p>
			The t-test provided on this website is a one-tailed test, given the prior assumption that the party which wins 
                        a majority of the seats in a state may have perpetrated a gerrymander. It assumes homoscedasticity, or that 
                        Democratic and Republican wins in non-gerrymandered states should be distributed equally.
		</p>
	<div>

	<hr>



</div>

<%@include file="/WEB-INF/views/include/footer.jsp" %>
