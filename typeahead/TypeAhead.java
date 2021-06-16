package interview.typeahead;

import java.util.*;

public class TypeAhead {

    TreeNode root;

    public TypeAhead(List<String> words) {
        root = new TreeNode();
        for (String word : words)
            root.insert(word);
    }

    public void findRecursive(TreeNode root, List<String> list, StringBuffer curr) {
        if (root.isWord) {
            list.add(curr.toString());
        }
        if (root.children != null && !root.children.isEmpty()) {
            for (TreeNode child : root.children.values()) {
                findRecursive(child, list, curr.append(child.c));
                curr.setLength(curr.length() - 1);
            }
        }
    }

    public List<String> find(String prefix) {
        List<String> list = new ArrayList<>();
        TreeNode lastNode = root;
        StringBuffer curr = new StringBuffer();
        for (char c : prefix.toCharArray()) {
            lastNode = lastNode.children.get(c);
            if (lastNode == null)
                return list;
            curr.append(c);
        }
        findRecursive(lastNode, list, curr);
        return list;
    }

    public static void main(String[] args) {

        List<String> words = Arrays.asList("Afghanistan","Albania","Algeria","American Samoa","Andorra",
                "Angola","Anguilla","Antarctica","Antigua and Barbuda","Argentina","Armenia","Aruba",
                "Australia","Austria","Azerbaijan","Bahamas (the)","Bahrain","Bangladesh","Barbados",
                "Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia (Plurinational State of)",
                "Bonaire, Sint Eustatius and Saba","Bosnia and Herzegovina","Botswana","Bouvet Island","Brazil",
                "British Indian Ocean Territory (the)","Brunei Darussalam","Bulgaria",
                "Burkina Faso","Burundi","Cabo Verde","Cambodia","Cameroon","Canada","Cayman Islands (the)",
                "Central African Republic (the)","Chad","Chile","China","Christmas Island",
                "Cocos (Keeling) Islands (the)","Colombia","Comoros (the)","Congo (the Democratic Republic of the)",
                "Congo (the)","Cook Islands (the)","Costa Rica","Croatia","Cuba","Curaçao","Cyprus","Czechia",
                "Côte d'Ivoire","Denmark","Djibouti","Dominica","Dominican Republic (the)","Ecuador",
                "Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Eswatini","Ethiopia",
                "Falkland Islands (the) [Malvinas]","Faroe Islands (the)","Fiji","Finland","France","French Guiana",
                "French Polynesia","French Southern Territories (the)","Gabon","Gambia (the)","Georgia",
                "Germany","Ghana","Gibraltar","Greece","Greenland","Grenada","Guadeloupe","Guam","Guatemala",
                "Guernsey","Guinea","Guinea-Bissau","Guyana","Haiti","Heard Island and McDonald Islands",
                "Holy See (the)","Honduras","Hong Kong","Hungary","Iceland","India","Indonesia",
                "Iran (Islamic Republic of)","Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica","Japan",
                "Jersey","Jordan","Kazakhstan","Kenya","Kiribati","Korea (the Democratic People's Republic of)",
                "Korea (the Republic of)","Kuwait","Kyrgyzstan","Lao People's Democratic Republic (the)","Latvia",
                "Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macao",
                "Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands (the)","Martinique",
                "Mauritania","Mauritius","Mayotte","Mexico","Micronesia (Federated States of)","Moldova (the Republic of)",
                "Monaco","Mongolia","Montenegro","Montserrat","Morocco","Mozambique","Myanmar","Namibia");

        TypeAhead typeAhead = new TypeAhead(words);
        System.out.println("In: " +typeAhead.find("In"));
        System.out.println("Af: " +typeAhead.find("Af"));
        System.out.println("Gu: " +typeAhead.find("Gu"));
        System.out.println("Bo: " +typeAhead.find("Bo"));
        System.out.println("Aust: " +typeAhead.find("Aust"));

    }

}
