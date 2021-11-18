#include <iostream>
#include "Main.h"
using namespace std;

/*
 * @autor JeanCarlosSC
 */

int main() {
    int n; //save world size
    string in = ""; //save user inputs

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
}

void execute(BlockWorld world, string instruction) {
    if(instruction == "quit") {
        return;
    }

    Tokens tokens = instruction.tokenizer();
    string action = tokens.next();
    int a = tokens.next();
    string direction = tokens.next();
    int b = tokens.next();

    if(a == b) {
        return;
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
}


