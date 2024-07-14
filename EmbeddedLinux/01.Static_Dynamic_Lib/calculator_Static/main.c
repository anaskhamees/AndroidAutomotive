/*this is the main application (calculator)*/
#include "includes/add.h"
#include "includes/div.h"
#include "includes/mod.h"
#include "includes/multi.h"
#include "includes/sub.h"
#include <stdio.h>

int main(void)
{
  printf("Add operation 5 + 5  =  %f\n",add_fun(5,5));
  printf("Sub operation 10 - 5 =  %f\n",sub_fun(10,5));
  printf("Multi operation 4 * 4=  %f\n",multi_fun(4,4));

   return 0;
}
