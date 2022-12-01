import React from 'react';
import RadioGroup from '@mui/material/RadioGroup';
import Radio from '@mui/material/Radio';
import FormLabel from '@mui/material/FormLabel';
import FormControlLabel from '@mui/material/FormControlLabel';

export default class QuestionList extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			questions: [],
			answerChecks: {}
		}
	}

	componentDidMount() {
		this.QuestionList();
	}

	QuestionList() {
		fetch('http://localhost:8080/questions')
			.then(response => response.json())
			.then(data => {
				this.setState({ questions: data })
				for (const question of data) {
					if (!(question.id in this.state.answerChecks)) {
						this.state.answerChecks[question.id] = {};
					}
					for (const answer of question.answers) {
						this.state.answerChecks[question.id][answer] = "Unknown"
					}
				}
			});
	}

	handleChange(event) {
		const answer = event.target.value;
		const questionId = event.target.id;

		fetch('http://localhost:8080/checkanswers', {
			method: 'post',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				questionId: event.target.id,
				answer: event.target.value
			})
		})
		.then(response => response.json())
		.then(answerIsCorrect => {
			this.setState(prevState => ({
				answerChecks: {
					...prevState.answerChecks,
					[parseInt(questionId)]: {
						...prevState[parseInt(questionId)],
						[answer]: answerIsCorrect.toString()
					}
				}
			}));
		});
	}

	render() {
		const questions = this.state.questions.map((question, i) => (
			<div key={ i }>
				<FormLabel id={ question["id"] }>{ question["question"] }</FormLabel>
		        <RadioGroup
		            name={ question.question }
		            onChange={this.handleChange.bind(this)}
		          >
		          { question["answers"].map((answer, j) => (
		          	<div key={ j } checkanswer={ this.state.answerChecks[question.id][answer] }>
		          		<FormControlLabel value={ answer } control={<Radio inputProps={{ 'id': question["id"] }} />} label={ answer } /> 
		          	</div>
		          )) }
		        </RadioGroup><br />
	        </div>
		));
		return <div>{ questions }</div>;
	}
}