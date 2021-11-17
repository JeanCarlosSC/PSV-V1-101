#include <iostream>
using namespace std;

int execute(blockWorld world, string instruction) {
    if(instruction == "quit") {
        return 0;
    }

    Tokens tokens = instruction.tokenizer();
    string action = tokens.next();
    int a = tokens.next();
    string direction = tokens.next();
    int b = tokens.next();

    if(a == b) {
        return 0;
    }
    
    if(action == "move") {
        if(direction == "onto") {
            world.moveOnto(a, b);
        }
        else if(direction == "over") {
            world.moveOver(a, b);
        }
    }
    else if(action == "pile") {
        if(direction == "onto") {
            world.pileOnto(a, b);
        }
        else if(direction == "over") {
            world.pileOver(a, b);
        }
    }
    
    return 0;
}

int main() {
    int n;
    string in = "";

    while(in != "quit") {
        cout << "type n (0 < n < 25)";
        cin >> in;
        n = int(in);

        if(n<=0 || n>=25)
            continue;
        
        BlockWorld world = new BlockWorld(n);

        while(in != "quit") {
            cout << "type an instruction";
            cin >> in;

            execute(world, in);
        }
    }

    cout << world.toString();
    return 0;
}