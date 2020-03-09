var Yatzy = function () {

    let _sumAllValues = function (dices) {
		return _sumAllValuesOf(dices);
	};

	let _sumAllValuesOf = function(dices,valueToSum) {
        var total = 0;
        for(let i = 0; i<dices.length;i++){
            if (!valueToSum || valueToSum == dices[i]) {
				total += dices[i];
			}
        }
        return total;
    };
    
    let _distinctValues = function (dices) {
		let distinctDices = [];
        for(let i = 0; i<dices.length;i++){
			if (distinctDices.indexOf(dices[i])==-1) {
				distinctDices.push(dices[i]);
			}
        }
        
		return distinctDices;
	}

	let _sumFirstValueOfKind = function (nbOfKind, dices) {
        let distinctDicesValues = _distinctValues(dices);

        for(let i = 0; i<distinctDicesValues.length;i++){
            let sum = _sumNbOccurenceOfValue(distinctDicesValues[i], nbOfKind, dices);
            if (sum > 0) {
                return sum;
            }
        }      
		return 0;
	}

	let _sumNbOccurenceOfValue = function (value,nbOccurence, dices) {
		if (((_sumAllValuesOf(dices,value)) / value) >= nbOccurence) {
			return value * nbOccurence;
		}
		return 0;
    }
    
    let _straight = function(dices) {
		return _distinctValues(dices).length == 5 ? _sumAllValues(dices) : 0;
	}

    return {

        ones: function () {
            return _sumAllValuesOf(arguments,1);
        }
        ,
        twos: function () {
            return _sumAllValuesOf(arguments,2);
      
        },

        threes: function () {
            return _sumAllValuesOf(arguments,3);
      
        },

        fours: function () {   
            return _sumAllValuesOf(arguments,4);
        }

        ,

        fives: function () {
            return _sumAllValuesOf(arguments,5);
        },

        sixes: function () {   
            return _sumAllValuesOf(arguments,6);
        },

        chance: function () {
            return _sumAllValues(arguments) ;
        },
        yatzy: function () {
            return _distinctValues(arguments).length == 1 ? 50 : 0;
        },

    
        score_pair: function () {
            var maxSumPair = 0;
            let distinctDicesValues = _distinctValues(arguments);
        
            
            for(let i = 0; i<distinctDicesValues.length;i++){
                let sumPairScore = _sumNbOccurenceOfValue(distinctDicesValues[i], 2, arguments);
                if (sumPairScore > maxSumPair) {
                    maxSumPair = sumPairScore;
                }
            }

            return maxSumPair;
        },

        two_pair : function () {
            var total = 0;
            let distinctDicesValues = _distinctValues(arguments);

            for(let i = 0; i<distinctDicesValues.length;i++){
                total += _sumNbOccurenceOfValue(distinctDicesValues[i], 2, arguments);
            }
            return total;
        },

        
        three_of_a_kind: function () {
            return _sumFirstValueOfKind(3, arguments);
        },

        four_of_a_kind: function () {
            return _sumFirstValueOfKind(4, arguments);
        },

        smallStraight: function () {
           return _straight(arguments);
        },

        largeStraight: function (dices) {
            return _straight(arguments);
        },

        fullHouse: function () {
            return _distinctValues(arguments).length == 2 ? _sumAllValues(arguments) : 0;
        }
    }
}();

module.exports = Yatzy;
