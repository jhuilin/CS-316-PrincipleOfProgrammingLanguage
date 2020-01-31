import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleShell {

	public static void main(String[] args) throws java.io.IOException{

		String commandLine;
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		ProcessBuilder pb = new ProcessBuilder();
		List<String> history = new ArrayList<String>();
		int index = 0;
		String OSName = System.getProperty("os.name");

		while(true){

			System.out.print("jsh>");
			if(OSName.toLowerCase().contains("windows".toLowerCase()))
				commandLine = "cmd.exe /c "+console.readLine();
			else
				commandLine=console.readLine();

			String[] commands = commandLine.split(" ");
			List<String> list = new ArrayList<String>();
			List<String> list2 = new ArrayList<String>();

			for(int i = 0;i<commands.length;i++)
			{
				list.add(commands[i]);
			}
			list2.add(commandLine);
			history.addAll(list2);
			try
			{
				if(list.get(list.size()-1).equals("history"))
				{
					for(String s : history)
						if(OSName.toLowerCase().contains("windows".toLowerCase()))
							System.out.println((index++) + " " +s.substring(11));
						else
							System.out.println((index++) + " " +s);
					index=0;
					continue;
				}

				if(list.contains("cd"))
				{
					if(list.get(list.size()-1).equals("cd"))
					{
						File home = new File(System.getProperty("user.home"));
						pb.directory(home);
						continue;
					}
					else
					{
						String dir = list.get(1);
						File newPath = new File(dir);
						boolean exists = newPath.exists();

						if(exists)
						{
							System.out.println("/" + dir);
							pb.directory(newPath);
							continue;
						}
						else
						{
							System.out.print("Path ");
						}
					}
				}

				if(list.get(list.size()-1).equals("!!"))
				{
					pb.command(history.get(history.size()-2));
				}
				else if
				(list.get(list.size()-1).charAt(0) == '!')
				{
					int b = Character.getNumericValue(list.get(list.size()-1).charAt(1));
					if(b<=history.size())
					pb.command(history.get(b));
				}
				else
				{
					pb.command(list);
				}
			Process process = pb.start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line;
			while((line = br.readLine()) != null)
				System.out.println(line);
			br.close();

			if(commandLine.equals(" "))
				continue;
			}
			catch (IOException e){
				System.out.println("Your input is error, Please enter again :)");
			}
		}
	}
}
