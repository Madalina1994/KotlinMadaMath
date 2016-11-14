import java.util.*

interface fact;

var fatherFacts: ArrayList<Father> = ArrayList()

var grandfatherFacts: ArrayList<Grandfather> = ArrayList()

class Father(var fatherName:String, var childName:String){
    init {
        addFather(this) //if the parent or the child exist already, save them as name+number. When you display them, you omit the number
    }
}


class Grandfather(var grandfatherName:String, var childName:String){
}

class And(firstFact:Father, secondFact:Father){
    init{
        if(firstFact.childName.equals(secondFact.fatherName)){
            var newGrandfather= Grandfather(firstFact.fatherName, secondFact.childName)
            grandfatherFacts.add(newGrandfather)
        }
    }
}

fun addFather(actualFather: Father):Unit{
    fatherFacts.add(actualFather)
}

fun addgrandFather(actualGrandfather: Grandfather):Unit{
    grandfatherFacts.add(actualGrandfather);
}

class query(relationship: String, firstPersonName: String, secondPersonName: String){
    var result: String= "? belongs to {"
    var resultList: ArrayList<String> = ArrayList()
    init{
        if(relationship.equals("Father") && !firstPersonName.equals("?") && !secondPersonName.equals("?")) {
            for(fatherFact in fatherFacts){
                if(fatherFact.fatherName.equals(firstPersonName) && fatherFact.childName.equals(secondPersonName)){
//                    result= "true"
                    resultList.add("true")
                }
            }
        }
        else if(relationship.equals("Grandfather") && !firstPersonName.equals("?") && !secondPersonName.equals("?")) {
            for(grandfatherFact in grandfatherFacts){
                if(grandfatherFact.grandfatherName.equals(firstPersonName) && grandfatherFact.childName.equals(secondPersonName)){
//                    result= "true"
                    resultList.add("true")
                }
            }
        }
        else if(relationship.equals("Father") && firstPersonName.equals("?") && !secondPersonName.equals("?")) {
            for(fatherFact in fatherFacts){
                if(fatherFact.childName.equals(secondPersonName)){
//                        result+= "? belongs to " + fatherFact.fatherName
                        resultList.add(fatherFact.fatherName)
                }
            }
        }
        else if(relationship.equals("Father") && !firstPersonName.equals("?") && secondPersonName.equals("?")) {
            for(fatherFact in fatherFacts){
                if(fatherFact.fatherName.equals(firstPersonName)){
                    resultList.add(fatherFact.childName)
                }
            }
        }
        for(resultElement in resultList){
                result+= resultElement + ", "
            }
        if(result.contains(", ", false)){
            result= result.substring(0, result.length-2)
        }
        result+= "}"
        }
    }

fun main(args: Array<String>){
    And(Father("Anders", "Dan"), Father("Dan", "Mark"))
    And(Father("Anders", "John"), Father("John", "Eric"))
    for(fatherFact in fatherFacts){
        println(fatherFact.fatherName + " is the father of "+ fatherFact.childName)
    }
    for(grandfatherFact in grandfatherFacts){
        println(grandfatherFact.grandfatherName + " is the grandfather of "+ grandfatherFact.childName)
    }
    println(query("Father", "Anders", "Dan").result)
    println(query("Grandfather", "Anders", "Mark").result)
    println(query("Father", "?", "Dan").result)
    println(query("Father", "Dan", "?").result)
}
